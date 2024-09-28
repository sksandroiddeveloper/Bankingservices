package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.digitalfintech.customer.databinding.ActivitySecondtimepinverificationBinding;
import com.squareup.picasso.Picasso;

public class Secondtimepinverification extends AppCompatActivity {
    ActivitySecondtimepinverificationBinding xml1;
    String pin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        xml1 = ActivitySecondtimepinverificationBinding.inflate(getLayoutInflater());
        setContentView(xml1.getRoot());
        xml1.pinView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
        String name = sh.getString("name", "");
        String email = sh.getString("email", "");
        String mobile = sh.getString("mobilenumber", "");
        String address = sh.getString("address", "");
        pin = sh.getString("pin", "");
        String profilepics = sh.getString("profile_pic","");
        Picasso.get().load(profilepics).into(xml1.textlogo);
        String fathername = sh.getString("fathername", "");
        String lastName = "";
        String firstName = "";

        xml1.forgetpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Otp_verification otp_verification = new Otp_verification(Secondtimepinverification.this,"");
                otp_verification.setCancelable(false);
                otp_verification.show();
            }
        });
        if (name.split("\\w+").length > 1) {
            lastName = name.substring(name.lastIndexOf(" ") + 1);
            firstName = name.substring(0, name.lastIndexOf(' '));
            char firstChar = firstName.charAt(0);
            String s = "" + firstChar;
           // xml1.textlogo.setText(s);
        } else {
            firstName = name;
        }

        xml1.textname.setText("Hi " + firstName);
        xml1.verifyNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String number =  xml1.pinView.getText().toString();

                    if (pin.equalsIgnoreCase(number)) {
                        MediaPlayer music = MediaPlayer.create(Secondtimepinverification.this, R.raw.loginsound);
                        music.start();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                        finish();
                    } else {

                        Toast.makeText(Secondtimepinverification.this, "Check and try again", Toast.LENGTH_SHORT).show();
                    }

                }


        });
      xml1.notyou.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
              startActivity(intent);
              finish();
          }
      });

    }
}