package com.digitalfintech.customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Qrcoder.QrcodeGenerator;
import com.digitalfintech.customer.Qrcoder.Qrmodel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.digitalfintech.customer.databinding.ActivityNavigationsliderBinding;
import com.squareup.picasso.Picasso;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationsliderBinding binding;
    boolean doubleBackToExitPressedOnce = false;
    String mobile_number,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationsliderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // binding.appBarNavigationslider.titles.setTitle("Shantanu");
        setSupportActionBar(binding.appBarNavigationslider.toolbars);
        SharedPreferences sharedPref = getSharedPreferences("Location", MODE_PRIVATE);
        String location = sharedPref.getString("currentlocation", "");
         binding.appBarNavigationslider.location.setText(location);


         data(mobile_number,type);


        binding.appBarNavigationslider.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }

        });


////        /
//        Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.appbarlayout_tool_bar);
//        toolbar.setTitle("This is toolbar.");
//        setSupportActionBar(toolbar);
        binding.navView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(menuItem -> {
            open();
//            Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
//            startActivity(loginActivity);
//            finish();

            // Toast.makeText(getApplicationContext(), "hello ", Toast.LENGTH_SHORT).show();
            return true;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String names = sharedPreferences.getString("name", ""); //mobilenumber
        String emails = sharedPreferences.getString("email", "");
        String profilepic = sharedPreferences.getString("profile_pic","");
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        String person_name = names;
        String email = emails;
        View hView = navigationView.getHeaderView(0);
        View hview = navigationView.getHeaderView(0);
        TextView nav_user = (TextView) hView.findViewById(R.id.person_name);
        nav_user.setText(person_name);
        TextView nav_name = (TextView) hview.findViewById(R.id.email);
        nav_name.setText(email);
        ImageView imageView = (ImageView)hview.findViewById(R.id.profile_images);
        Picasso.get().load(profilepic).into(imageView);
        SharedPreferences preferences = getSharedPreferences("myKey", MODE_PRIVATE);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigationslider);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    private void data(String mobile_number, String type) {

        SharedPreferences sh = getSharedPreferences("Login", MODE_PRIVATE);
        mobile_number =sh.getString("mobile_numbes","");
        type= "fetch";
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Qrmodel> call = apiInterface.wallet(mobile_number,type);
        call.enqueue(new Callback<Qrmodel>() {

            @Override

            public void onResponse(Call<Qrmodel> call, Response<Qrmodel> response) {

                if (response.body().getStatus_message().equalsIgnoreCase( "Account balance found"))
                {
                    String amount =   response.body().getData().getAccount_balance();
                    SharedPreferences sharedPref = getSharedPreferences("amount", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("amount",amount);
                    editor.apply();
                    //Toast.makeText(Dashboard.this, ""+amount, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(QrcodeGenerator.this, "upi"+upi, Toast.LENGTH_SHORT).show();


                }
                else{
//                    progressBar.dismiss();
                    Toast.makeText(Dashboard.this, "Something went Wrong Try Again Later !! ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Qrmodel> call, Throwable t) {
                Toast.makeText(Dashboard.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigationslider, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigationslider);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void open() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Dashboard.this);
        alertDialog.setMessage("Do you want to exit the app? ");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String logout;
                logout  = "2";
                SharedPreferences sharedPreferences = getSharedPreferences("Logout",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("logout",logout);
                myEdit.commit();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

                // Toast.makeText(Main_Dashboard.this,"you clicked on yes button",Toast.LENGTH_LONG).show();

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();

    }

    public void onBackPressed()
    {
        //Checking for fragment count on backstack
      open();
    }
}