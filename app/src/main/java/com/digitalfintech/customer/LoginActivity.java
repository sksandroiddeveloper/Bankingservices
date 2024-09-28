package com.digitalfintech.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Modelclass.Loginenternumber;
import com.digitalfintech.customer.Modelclass.Verified_pin;
import com.digitalfintech.customer.databinding.ActivityLoginBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding login;
    String types, otp;
    boolean doubleBackToExitPressedOnce = false;
    Intent intent;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(login.getRoot());
        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        login.gsymbol.startAnimation(aniRotate);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getlocation();

        } else {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

        }
        login.forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp = new Intent(getApplicationContext(), Forgetpin.class);
                startActivity(fp);
                if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getlocation();

                } else {
                    ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

            }
        });
        login.btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (login.etmobile.getText().toString().isEmpty())
                {
                    if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        getlocation();

                    } else {
                        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                    }

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.logintoast, (ViewGroup) findViewById(R.id.toast_layout_root));
                    // get the reference of TextView and ImageVIew from inflated layout
                    TextView toastTextView = (TextView) layout.findViewById(R.id.toastTextView);
                    ImageView toastImageView = (ImageView) layout.findViewById(R.id.toastImageView);
                    // set the text in the TextView
                    toastTextView.setText("Enter Mobile Number");
                    // set the Image in the ImageView
                    //    toastImageView.setImageResource(R.drawable.wallet);
                    // create a new Toast using context

                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG); // set the duration for the Toast
                    toast.setView(layout); // set the inflated layout
                    toast.show();

                    //    Toast.makeText(LoginActivity.this, "Enter Mobile Number", Toast.LENGTH_LONG).show();

                    return;
                } else
                {
                    if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        getlocation();
                        postData(login.etmobile.getText().toString(), types);

                    } else {
                        ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                        postData(login.etmobile.getText().toString(), types);
                    }

                }


            }
        });


    }

    private void postData(String mobile_number, String type) {
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Loginenternumber> call = apiInterface.userlogincheck(mobile_number, "push_auth");
        call.enqueue(new Callback<Loginenternumber>() {
            @Override
            public void onResponse(Call<Loginenternumber> call, Response<Loginenternumber> response) {

                if (response.body().getStatus_message().equalsIgnoreCase("Login Using Pin") && response.body().getStatus().equalsIgnoreCase("pin_exist")) {
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("response", response.body().getStatus());
                    myEdit.putString("mobile_numbes", mobile_number);
                    myEdit.commit();
                    SharedPreferences shared = getSharedPreferences("myKey", MODE_PRIVATE);
                    SharedPreferences.Editor my = shared.edit();
                    my.putString("value", mobile_number);
                    my.commit();
                    MediaPlayer music = MediaPlayer.create(LoginActivity.this, R.raw.loginsound);
                    music.start();
                    Intent fp = new Intent(getApplicationContext(), FirstLoginwithPin.class);
                    fp.putExtra("number", mobile_number);
                    startActivity(fp);
                } else {

                    if (response.body().getStatus_message().equalsIgnoreCase("OTP Sent Successfully.")) {
                        String mobile_numbers = login.etmobile.getText().toString();
                        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        String types = "verify_auth";
                        myEdit.putString("types", types);
                        myEdit.putString("mobile_number", mobile_numbers);
                        myEdit.commit();
                        LayoutInflater inflater = getLayoutInflater();
                        Toast.makeText(LoginActivity.this, "OTP Sent Successfully Mobile Number", Toast.LENGTH_LONG).show();
                        Otp_verification otp_verification = new Otp_verification(LoginActivity.this, "");
                        otp_verification.setCancelable(false);
                        otp_verification.show();
                        otp_verification.otpverifed.verifyNumber.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                if (otp_verification.otpverifed.pinView.getText().toString().isEmpty()) {
                                    Toast.makeText(LoginActivity.this, "Enter the Otp Try Again", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    otp = otp_verification.otpverifed.pinView.getText().toString();
                                    otpname(mobile_number, type, otp);
                                    otp_verification.dismiss();
                                }
                            }
                        });


                    } else
                    {
                        Toast.makeText(LoginActivity.this, "Mobile Number Does not Exists", Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<Loginenternumber> call, Throwable t)
            {

                //  Toast.makeText(LoginActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();

            }
        });


    }

    private void otpname(String mobile_number, String type, String otp)
    {
        SharedPreferences sh = getSharedPreferences("Login", MODE_PRIVATE);
        mobile_number = sh.getString("mobile_number", "");
        type = sh.getString("types", "");
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Verified_pin> call = apiInterface.verifypin(mobile_number, type, otp);
        call.enqueue(new Callback<Verified_pin>()
        {
            @Override
            public void onResponse(Call<Verified_pin> call, Response<Verified_pin> response) {
                if (response.body().getStatus_message().equalsIgnoreCase("OTP matched Successfully.")) {

                    Intent fp = new Intent(getApplicationContext(), Changeapin.class);
                    startActivity(fp);
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);

                }
            }

            @Override
            public void onFailure(Call<Verified_pin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }




    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }

    private void getlocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {

                    Geocoder geocoder = new Geocoder(LoginActivity.this, Locale.getDefault());

                    try {
                        List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        //
                        //  xml.location.setText(address.get(0).getLocality());
                        String currentlocation = address.get(0).getLocality().toString();
                        SharedPreferences sharedPreferences = getSharedPreferences("Location", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("currentlocation", currentlocation);
                        myEdit.commit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });
    }
}

