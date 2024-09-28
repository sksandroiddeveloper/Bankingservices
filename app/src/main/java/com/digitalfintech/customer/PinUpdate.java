package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.digitalfintech.customer.databinding.ActivityPinUpdateBinding;

public class PinUpdate extends AppCompatActivity {
 ActivityPinUpdateBinding pinupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pinupdate = ActivityPinUpdateBinding.inflate(getLayoutInflater());
        setContentView(pinupdate.getRoot());
        pinupdate.successMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(update);
            }
        });

    }
}