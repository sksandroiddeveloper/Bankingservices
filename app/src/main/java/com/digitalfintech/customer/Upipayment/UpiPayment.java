package com.digitalfintech.customer.Upipayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Dashboard;
import com.digitalfintech.customer.R;
import com.digitalfintech.customer.ScanandPay.Sacnandpay;
import com.digitalfintech.customer.Secondtimepinverification;
import com.digitalfintech.customer.databinding.ActivityUpiPaymentBinding;
import com.digitalfintech.customer.transactionst.TransactionDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rk.libcurrencyview.CurrencyEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpiPayment extends AppCompatActivity {
    ActivityUpiPaymentBinding upi;
    String bnname, upiid, descrip, id, amounts;
    Integer amountes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        upi = ActivityUpiPaymentBinding.inflate(getLayoutInflater());
        setContentView(upi.getRoot());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        CurrencyEditText currency_edt = findViewById(R.id.enteramount);
        currency_edt.setCurrency("₹");
        upi.bottomNavigation.setSelectedItemId(R.id.scan);
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        // display the string into textView
         upi.upiidedit.setText(str);
        upi.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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


        upi.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(fp);
            }
        });
        upi.upipayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (upi.enteramount.getText().toString().isEmpty()) {
                    upi.enteramount.setError("Enter Amount");
                } else if (upi.enterName.getText().toString().isEmpty()) {
                    upi.enterName.setError("Enter Name");

                } else if (upi.upiidedit.getText().toString().isEmpty()) {
                    upi.upiidedit.setError("Enter Upi I.D");

                } else if (upi.note.getText().toString().isEmpty()) {
                    upi.note.setError("Enter Description");

                } else {

                    //  amount =upi.enteramount.getText().toString();
                    String[] as = upi.enteramount.getText().toString().split("₹");
                    amounts = (String.valueOf(as[1]));
                    String i = amounts.replaceAll("\\s", "");
                    amountes = Integer.parseInt(i);
                    bnname = upi.enterName.getText().toString();
                    upiid = upi.upiidedit.getText().toString();
                    descrip = upi.note.getText().toString();
                    // Toast.makeText(UpiPayment.this, "Sending", Toast.LENGTH_SHORT).show();
                    Securityupi securityupi = new Securityupi(UpiPayment.this, "");
                    securityupi.setCancelable(false);
                    securityupi.show();
                    securityupi.upisecurity.pinView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    securityupi.upisecurity.cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            securityupi.dismiss();
                        }
                    });
                    securityupi.upisecurity.verifyNumber.setOnClickListener(new View.OnClickListener() {
                        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
                        String login_pin = sh.getString("loginpin", "");
                        String ides = sh.getString("id", "");

                        @Override
                        public void onClick(View v) {

                            if (securityupi.upisecurity.pinView.getText().toString().isEmpty()) {
                                Toast.makeText(UpiPayment.this, "Enter the Otp Try Again", Toast.LENGTH_SHORT).show();

                            } else if (securityupi.upisecurity.pinView.getText().toString().equalsIgnoreCase(login_pin)) {
                                securityupi.upisecurity.progress.setVisibility(View.VISIBLE);
                                id = ides;
                                verfiy(id, amountes, bnname, upiid, descrip);
                                securityupi.upisecurity.verifyNumber.setBackgroundColor(Color.GRAY);
                                Toast.makeText(UpiPayment.this, "Please Wait", Toast.LENGTH_SHORT).show();
                                securityupi.upisecurity.verifyNumber.setEnabled(false);

//                                   new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        securityupi.upisecurity.verifyNumber
//                                                .setEnabled(false);
//                                        securityupi.upisecurity.verifyNumber.setBackgroundColor(Color.GREEN);
//                                      //  Toast.makeText(UpiPayment.this, "2", Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }, 40000);


                            } else {
                                Toast.makeText(UpiPayment.this, "Check Your Pin! Try Again", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }

            }
        });
    }

    private void verfiy(String user_id, int amount, String beneficiary_name, String upi_id, String note) {
        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
        user_id = sh.getString("id", "");

        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Upipay> call = apiInterface.upipayment(user_id, amount, beneficiary_name, upi_id, note);
        call.enqueue(new Callback<Upipay>() {
            @Override
            public void onResponse(Call<Upipay> call, Response<Upipay> response) {
//                Toast.makeText(UpiPayment.this, "CORRECT", Toast.LENGTH_SHORT).show();
                if (response.body().getStatus_message().equalsIgnoreCase("UPI Transfer Processed Successfully")) {
                    MediaPlayer music = MediaPlayer.create(UpiPayment.this, R.raw.loginsound);
                    music.start();
                    String refernceid = response.body().getData().getReference_id();
                    String name = response.body().getData().getBeneficiary_name();
                    String utrnuber = response.body().getData().getUtr_no();

                    Intent fp = new Intent(getApplicationContext(), Paymentsuccess.class);
                    fp.putExtra("refernceid", refernceid);
                    fp.putExtra("name", name);
                    fp.putExtra("utrnuber", utrnuber);
                    startActivity(fp);

                    Toast.makeText(UpiPayment.this, "refernceid" + refernceid, Toast.LENGTH_SHORT).show();

                } else {
//                    progressBar.dismiss();
                    //    Toast.makeText(UpiPayment.this, "Transaction Failed! ", Toast.LENGTH_SHORT).show();

                    Intent fp = new Intent(getApplicationContext(), Failure.class);
                    startActivity(fp);

                }
            }

            @Override
            public void onFailure(Call<Upipay> call, Throwable t) {
                Toast.makeText(UpiPayment.this, "NOT CORRECT", Toast.LENGTH_SHORT).show();
                upi.idPBLoading.setVisibility(View.INVISIBLE);
                Toast.makeText(UpiPayment.this, "Something wrong " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Intent fp = new Intent(getApplicationContext(), UpiPayment.class);
                startActivity(fp);
            }
        });


    }

}
