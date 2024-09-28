package com.digitalfintech.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.digitalfintech.customer.ScanandPay.Sacnandpay;
import com.digitalfintech.customer.Upipayment.UpiPayment;
import com.digitalfintech.customer.databinding.ActivityContactusBinding;
import com.digitalfintech.customer.transactionst.TransactionDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Contactus extends AppCompatActivity {
    ActivityContactusBinding contact;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        contact = ActivityContactusBinding.inflate(getLayoutInflater());
        setContentView(contact.getRoot());
        contact.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        item.setIcon(R.drawable.wallet);
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


        contact.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }
}