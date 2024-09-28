package com.digitalfintech.customer.Upipayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.digitalfintech.customer.Dashboard;
import com.digitalfintech.customer.R;
import com.digitalfintech.customer.databinding.ActivityPaymentsuccessBinding;

public class Paymentsuccess extends AppCompatActivity {
    ActivityPaymentsuccessBinding payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payment = ActivityPaymentsuccessBinding.inflate(getLayoutInflater());
        setContentView(payment.getRoot());
        onBackPressed();
        Intent intent = getIntent();
        String refe = intent.getStringExtra("refernceid");
        payment.ref.setText("Refernce id " + refe);
        String names = intent.getStringExtra("name");
        payment.name.setText("Name:" + names);
        String utnumber = intent.getStringExtra("utrnuber");
        payment.utrno.setText("UTR No :" + utnumber);
        payment.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(fp);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}