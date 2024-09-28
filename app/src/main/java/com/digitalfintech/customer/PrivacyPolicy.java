package com.digitalfintech.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.digitalfintech.customer.ScanandPay.Sacnandpay;
import com.digitalfintech.customer.Upipayment.UpiPayment;
import com.digitalfintech.customer.databinding.ActivityPrivacyPolicyBinding;
import com.digitalfintech.customer.transactionst.TransactionDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrivacyPolicy extends AppCompatActivity {
ActivityPrivacyPolicyBinding privacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        privacy = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        setContentView(privacy.getRoot());
        privacy.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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




        privacy.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent fp = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(fp);
            }
        });

    }
}