package com.digitalfintech.customer.Upipayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.digitalfintech.customer.Dashboard;
import com.digitalfintech.customer.R;
import com.digitalfintech.customer.databinding.ActivityFailureBinding;

public class Failure extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failure);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent
                Intent i = new Intent(Failure.this, Dashboard.class);
                // on below line we are
                // starting a new activity.
                startActivity(i);
                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 2000);



    }
}