package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Modelclass.Firstpinenter;
import com.digitalfintech.customer.Modelclass.Forget_Pin;
import com.digitalfintech.customer.Modelclass.Loginenternumber;
import com.digitalfintech.customer.Modelclass.Verified_pin;
import com.digitalfintech.customer.databinding.ActivityFirstLoginwithPinBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstLoginwithPin extends AppCompatActivity {
ActivityFirstLoginwithPinBinding firstlogin;
String number,type,login_pin,mobilenumber,mobile_number;
    String types, otp;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstlogin = ActivityFirstLoginwithPinBinding.inflate(getLayoutInflater());
        setContentView(firstlogin.getRoot());
        firstlogin.pinView.getText().toString();
        firstlogin.pinView.setTransformationMethod(PasswordTransformationMethod.getInstance());



        firstlogin.forgetpin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Otp_verification otp_verification = new Otp_verification(FirstLoginwithPin.this,"");
                otp_verification.setCancelable(false);
                otp_verification.show();

                forgetpin(mobilenumber,type);

                otp_verification.otpverifed.verifyNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (otp_verification.otpverifed.pinView.getText().toString().isEmpty()) {
                            Toast.makeText(FirstLoginwithPin.this, "Enter the Otp Try Again", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            otp = otp_verification.otpverifed.pinView.getText().toString();
                            otpname(mobile_number, types, otp);
                            otp_verification.dismiss();
                        }
                    }
                });





            }
        });
        firstlogin.verifyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinverification(number,type,login_pin);

            }
        });



    }

    private void otpname(String mobilenumbers, String type, String otp) {


        SharedPreferences sh = getSharedPreferences("Login", MODE_PRIVATE);
        mobilenumbers = sh.getString("mobile_numbes", "");
        type = "verify_auth";
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Verified_pin> call = apiInterface.verifypin(mobilenumbers, type, otp);
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
                Toast.makeText(FirstLoginwithPin.this, "Throwable"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    

    private void forgetpin (String mobile_number, String type) {

        SharedPreferences sh = getSharedPreferences("Login", MODE_PRIVATE);
        mobile_number = sh.getString("mobile_numbes", "");
        type = "forget_pin";
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Forget_Pin> call = apiInterface.forgetpin(mobile_number, type);
        call.enqueue(new Callback<Forget_Pin>() {
            @Override
            public void onResponse(Call<Forget_Pin> call, Response<Forget_Pin> response) {


                if (response.body() != null) {
                    if (response.body().getStatus_message().equalsIgnoreCase("OTP Sent Successfully."))
                    {
                        Toast.makeText(FirstLoginwithPin.this, "OTP Sent Successfully.", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(FirstLoginwithPin.this, "Mobile Number Does not Exists", Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<Forget_Pin> call, Throwable t) {

                Toast.makeText(FirstLoginwithPin.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();

            }
        });


    }

    private void pinverification(String mobile_number,String type,String login_pin ) {
          login_pin=  firstlogin.pinView.getText().toString();
         mobile_number = getIntent().getExtras().getString("number");
//        char firstChar = login_pin.charAt(0);
//
//        firstlogin.pinView.setText(firstChar);
         type = "login_with_pin";
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Firstpinenter> call = apiInterface.userpincheckfirst(mobile_number,type,login_pin);
        call.enqueue(new Callback<Firstpinenter>() {
            @Override
            public void onResponse(Call<Firstpinenter> call, Response<Firstpinenter> response) {
                if (response.body().getStatus().equalsIgnoreCase( "success"))

                { String loginpin = response.body().getData().getUser_details().getLogin_pin();
                    String id = response.body().getData().getUser_details().getId();
                   String accountnumber = response.body().getData().getVirtual_account().getRbl_bank().getAccount_number();
                   String accountbalnace = response.body().getData().getUser_details().getAccount_balance();
                   String email = response.body().getData().getUser_details().getEmail();
                   String name = response.body().getData().getUser_details().getMember_name();
                   String mobilenumber = response.body().getData().getUser_details().getMobilenumber();
                   String address = response.body().getData().getUser_details().getAddress();
                   String fathername = response.body().getData().getUser_details().getMember_father_name();
                   String profilepic = response.body().getData().getUser_details().getProfile_pic();
                   String pin = firstlogin.pinView.getText().toString();
                   String secondnumber = "1";
                   String ifccode = response.body().getData().getVirtual_account().getRbl_bank().getAccount_ifsc();
                    SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("id", id);
                    editor.putString("loginpin",loginpin);
                    editor.putString("accountnumber", accountnumber);
                    editor.putString("accountbalnace",accountbalnace);
                    editor.putString("email",email);
                    editor.putString("name",name);
                    editor.putString("address",address);
                    editor.putString("fathername",fathername);
                    editor.putString("mobilenumber",mobilenumber);
                    editor.putString("ifccode",ifccode);
                    editor.putString("secondlogin",secondnumber);
                    editor.putString("pin",pin);
                    editor.putString("profile_pic",profilepic);
                    editor.apply();

                    MediaPlayer music = MediaPlayer.create(FirstLoginwithPin.this, R.raw.loginsound);
                    music.start(); // sound start from
  //                  progressBar.dismiss();
                    Intent fp = new Intent(getApplicationContext(), Dashboard.class);
//
                    startActivity(fp);
                }
                else{
//                    progressBar.dismiss();
                    Toast.makeText(FirstLoginwithPin.this, "Check your PIN Try Again! ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Firstpinenter> call, Throwable t) {
                Toast.makeText(FirstLoginwithPin.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();
            }
        });




    }
}