package com.digitalfintech.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBindings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Modelclass.Updatepin;
import com.digitalfintech.customer.ScanandPay.Sacnandpay;
import com.digitalfintech.customer.Upipayment.QrPament;
import com.digitalfintech.customer.Upipayment.UpiPayment;
import com.digitalfintech.customer.databinding.ActivityMpindigitBinding;
import com.digitalfintech.customer.transactionst.TransactionDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeMpindigit extends AppCompatActivity {
    ActivityMpindigitBinding pin;
    String number, type, login_pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pin = ActivityMpindigitBinding.inflate(getLayoutInflater());
        setContentView(pin.getRoot());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homes:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.scan:
                        item.setIcon(R.drawable.scan);
                        startActivity(new Intent(getApplicationContext(), Sacnandpay.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.walllets:
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.statement:
                        startActivity(new Intent(getApplicationContext(), TransactionDetails.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        pin.verifyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pin.pinView.getText().toString().equals(pin.pinView1.getText().toString())) {
                    pinverification(number, type, login_pin);
                } else {

                    Toast.makeText(ChangeMpindigit.this, "Check Your Pin and Try Again!", Toast.LENGTH_SHORT).show();

                }


            }
        });

        pin.backarrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }

        });


    }

    private void pinverification(String number, String type, String login_pin) {
        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
        number = sh.getString("mobilenumber", "");

        login_pin = pin.pinView.getText().toString();
        type = "set_pin";

        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Updatepin> call = apiInterface.updatepin(number, type, login_pin);
        call.enqueue(new Callback<Updatepin>() {

            @Override

            public void onResponse(Call<Updatepin> call, Response<Updatepin> response) {

                if (response.body().getStatus().equalsIgnoreCase("pin_updated")) {

                    MediaPlayer music = MediaPlayer.create(ChangeMpindigit.this, R.raw.loginsound);
                    music.start(); // sound start from
                    //                  progressBar.dismiss();
                    Intent fp = new Intent(getApplicationContext(), PinUpdate.class);
                    startActivity(fp);
                } else {
//                    progressBar.dismiss();
                    Toast.makeText(ChangeMpindigit.this, "Check your PIN Try Again! ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Updatepin> call, Throwable t) {
                Toast.makeText(ChangeMpindigit.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();
            }
        });

    }


}