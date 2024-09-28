package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.digitalfintech.customer.databinding.ActivityOtpVerificationAcitivty2Binding;
import com.digitalfintech.customer.databinding.ActivityOtpVerificationBinding;

public class OtpVerificationAcitivty2 extends AppCompatActivity {
    ActivityOtpVerificationAcitivty2Binding otpverifed;
    String mobile_number,type,otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(otpverifed.getRoot());



    }



}