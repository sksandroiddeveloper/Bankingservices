package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Modelclass.Updatepin;
import com.digitalfintech.customer.databinding.ActivityChangeapinBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Changeapin extends AppCompatActivity {
ActivityChangeapinBinding change ;
    String number,type,login_pin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        change = ActivityChangeapinBinding.inflate(getLayoutInflater());
        setContentView(change.getRoot());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        change.verifyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (change.pinView.getText().toString().equals(change.pinView1.getText().toString())) {
                    pinverification(number, type, login_pin);
                }

                else {

                    Toast.makeText(Changeapin.this, "Check Your Pin and Try Again!", Toast.LENGTH_SHORT).show();

                }


            }
        });

        change.backarrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }

        });


    }

    private void pinverification(String number , String type , String login_pin)
    {
        SharedPreferences sh = getSharedPreferences("Login", MODE_PRIVATE);
        number =sh.getString("mobile_numbes","");

        login_pin =  change.pinView.getText().toString();
        type ="set_pin";

        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Updatepin> call = apiInterface.updatepin(number,type,login_pin);
        call.enqueue(new Callback<Updatepin>() {

            @Override

            public void onResponse(Call<Updatepin> call, Response<Updatepin> response) {

                if (response.body().getStatus().equalsIgnoreCase( "pin_updated"))
                {

                    MediaPlayer music = MediaPlayer.create(Changeapin.this, R.raw.loginsound);
                    music.start(); // sound start from
                    //                  progressBar.dismiss();
                    Intent fp = new Intent(getApplicationContext(), PinUpdate.class);
//
                    startActivity(fp);
                }
                else{
//                    progressBar.dismiss();
                    Toast.makeText(Changeapin.this, "Check your PIN Try Again! ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Updatepin> call, Throwable t) {
                Toast.makeText(Changeapin.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();
            }
        });






    }
}

