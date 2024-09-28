package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.digitalfintech.customer.databinding.ActivityForgetpinBinding;

import java.util.regex.Matcher;

public class Forgetpin extends AppCompatActivity {
 ActivityForgetpinBinding forgetpin;
 Matcher m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetpin = ActivityForgetpinBinding.inflate(getLayoutInflater());
        setContentView(forgetpin.getRoot());



        forgetpin.forgetpins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

  //////////////////////////////////////////////////////// Check Your Mobile Number //////////////////////////////////////////////////////////////////////////////

        forgetpin.btnLogin.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {


                if (forgetpin.contactNumber.getText().toString().isEmpty()) {

                    Toast.makeText(Forgetpin.this, "Enter Your Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (android.util.Patterns.PHONE.matcher(forgetpin.contactNumber.getText().toString()).matches())
                    {
//                        Intent activity = new Intent(getApplicationContext(),Otp_verification.class);
//                         startActivity(activity);
                        Toast.makeText(Forgetpin.this, "Check Your Number", Toast.LENGTH_LONG).show();
                        Otp_verification otp_verification = new Otp_verification(Forgetpin.this,"");
                        otp_verification.setCancelable(false);
                        otp_verification.show();
                    }
                    else

                    {
                        Toast.makeText(Forgetpin.this, "Check Your Number", Toast.LENGTH_LONG).show();
//

                    }
                }
            }


        });




    }
}