package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.digitalfintech.customer.databinding.ActivityProfilephotoBinding;
import com.squareup.picasso.Picasso;

public class Profilephoto extends AppCompatActivity {
ActivityProfilephotoBinding profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profile = ActivityProfilephotoBinding.inflate(getLayoutInflater());
        setContentView(profile.getRoot());
 profile.back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         onBackPressed();
      }
  });
        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
        String name =sh.getString("name","");
        String email = sh.getString("email","");
        String mobile = sh.getString("mobilenumber","");
        String address = sh.getString("address","");
        String fathername = sh.getString("fathername","");
        String profilepics = sh.getString("profile_pic","");
        profile.topname.setText(name);
        profile.emailid.setText(email);
        Picasso.get().load(profilepics).into(profile.profileImage);
        profile.profilelayout.names.setText(name);
        profile.profilelayout.mobilenumbers.setText(mobile);
        profile.profilelayout.emialds.setText(email);
        profile.profilelayout.address.setText(address);
        profile.profilelayout.fathernames.setText(fathername);
        profile.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(fp);
            }
        });
    }

}