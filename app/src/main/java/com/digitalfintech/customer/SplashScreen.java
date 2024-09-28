package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.digitalfintech.customer.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
ActivitySplashScreenBinding acitivtysplash;
String number = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        acitivtysplash = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(acitivtysplash.getRoot());
        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        acitivtysplash.gsymbol.startAnimation(aniRotate);

        SharedPreferences sh = getSharedPreferences("myKey",MODE_PRIVATE);
        number =  sh.getString("secondlogin", "");

        if (number.equalsIgnoreCase("1") )
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // on below line we are
                    // creating a new intent
                    Intent i = new Intent(SplashScreen.this, Secondtimepinverification.class);

                    // on below line we are
                    // starting a new activity.
                    startActivity(i);

                    // on the below line we are finishing
                    // our current activity.
                    finish();
                }
            }, 2000);

        }

//        else if (number.equalsIgnoreCase("2"))
//        {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    // on below line we are
//                    // creating a new intent
//                    Intent i = new Intent(SplashScreen.this, Secondtimepinverification.class);
//
//                    // on below line we are
//                    // starting a new activity.
//                    startActivity(i);
//
//                    // on the below line we are finishing
//                    // our current activity.
//                    finish();
//                }
//            }, 2000);
//        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // on below line we are
                    // creating a new intent
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);

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

}