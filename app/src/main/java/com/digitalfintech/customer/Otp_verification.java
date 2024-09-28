package com.digitalfintech.customer;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Modelclass.Forget_Pin;
import com.digitalfintech.customer.Modelclass.Updatepin;
import com.digitalfintech.customer.Modelclass.Verified_pin;
import com.digitalfintech.customer.databinding.ActivityOtpVerificationBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Otp_verification extends Dialog
{
 ActivityOtpVerificationBinding otpverifed;

String mobile_number,type,otp;


    public Otp_verification(@NonNull Context context, String s)
    {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        otpverifed = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(otpverifed.getRoot());
        //  forgetpin(mobile_number,type);
//        otpverifed.verifyNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(otpverifed.pinView.getText().toString().isEmpty())
//                {
//                    Toast.makeText(getContext(), "Enter the Otp Code ", Toast.LENGTH_SHORT).show();
//
//                }
//                else
//                {
//                  otp  = otpverifed.pinView.getText().toString();
//                    verifiedotp(mobile_number,type,otp);
//                }
//            }
//        });

    }

//    private void verifiedotp(String mobile_number, String type, String otp)
//    {
//        SharedPreferences sh = getContext().getSharedPreferences("Login", MODE_PRIVATE);
//        mobile_number =sh.getString("mobile_number","");
//        type = sh.getString("types", "");
//        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
//        Call<Verified_pin> call = apiInterface.verifypin(mobile_number,type,otp);
//        call.enqueue(new Callback<Verified_pin>() {
//
//            @Override
//
//            public void onResponse(Call<Verified_pin> call, Response<Verified_pin> response)
//            {
//                if  (response.body().getStatus_message().equalsIgnoreCase("OTP matched Successfully."))
//                {
//
//                }
//                else
//
//                {
//                    Intent i = new Intent(getContext(), ChangeMpindigit.class);
////
//                    i.getAction();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Verified_pin> call, Throwable t) {
//                Toast.makeText(getContext(),"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
////
//            }
//        });
//
//    }
//

    }


