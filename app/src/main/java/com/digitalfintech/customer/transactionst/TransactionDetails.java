package com.digitalfintech.customer.transactionst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.ChangeMpindigit;
import com.digitalfintech.customer.Dashboard;
import com.digitalfintech.customer.MainActivity2;
import com.digitalfintech.customer.Modelclass.Updatepin;
import com.digitalfintech.customer.PinUpdate;
import com.digitalfintech.customer.R;
import com.digitalfintech.customer.Upipayment.UpiPayment;
import com.digitalfintech.customer.databinding.ActivityTransactionDetailsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionDetails extends AppCompatActivity {
    String user_id, start, start1, start2;
    List<Tranasc> transactions=new ArrayList<>();
    ActivityTransactionDetailsBinding tranasc;
    RecyclerView recyclerView;
    // creating variables for our UI components.
    int count = 0;
    String page,totalpage;
    // Store a member variable for the listener
    //private EndlessRecyclerViewScrollListener scrollListener;//jo tumne API lagai thi wo kahan h?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tranasc = ActivityTransactionDetailsBinding.inflate(getLayoutInflater());
        transactions = new ArrayList<>();
        setContentView(tranasc.getRoot());
        transactions.clear();
        tranasc.bottomNavigation.setSelectedItemId(R.id.statement);
        tranasc.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homes:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.scan:
                        item.setIcon(R.drawable.scan);
                        startActivity(new Intent(getApplicationContext(), UpiPayment.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.walllets:
                        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
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




        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
        String name =sh.getString("name","");
        String accountnumber = sh.getString("accountnumber","");

        tranasc.account2.setText(accountnumber);
        tranasc.name.setText(name);

        //start = "1";
        post(user_id, 0);//spet 1
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                post(user_id, 2);//spet 2
            }
        }, 1000);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                post(user_id, 4);//spet 1
            }
        }, 1000);
    }


    private void post(String userid, int start) {
        SharedPreferences sh = getSharedPreferences("myKey", MODE_PRIVATE);
        userid = sh.getString("id","");
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<TransacReport> call = apiInterface.transac(userid, start+"");
        call.enqueue(new Callback<TransacReport>() {

            @Override

            public void onResponse(Call<TransacReport> call, Response<TransacReport> response) {
                TransacReport transacReport = response.body();
                if (transacReport.status_message.equalsIgnoreCase("Report Successfully fetched")&&!transacReport.getData().getRecord_found().equalsIgnoreCase("0")) {
                    //transactions = transacReport.getData().getTransactions();
                    transactions.addAll(transacReport.getData().getTransactions());
                    page = transacReport.getData().getNext_page_start().toString();
                    totalpage = transacReport.getData().getTotal_records().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences("Page", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("page", page);
                    myEdit.commit();
                 //   Toast.makeText(TransactionDetails.this, ""+page, Toast.LENGTH_SHORT).show();
                    //transactions.addAll(transacReport.getData().getTransactions());

                    PutDataIntoRecyclerView(transactions,page,totalpage);

                }
                else

                {
                    tranasc.idPBLoading.setVisibility(View.INVISIBLE);
//                    progressBar.dismiss();
                    Toast.makeText(TransactionDetails.this, "No More Record", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TransacReport> call, Throwable t) {
                Toast.makeText(TransactionDetails.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();
            }
        });
    }

    private void PutDataIntoRecyclerView(List<Tranasc> transactions,String page,String totalpage) {
        Adaptertransac adaptertransac = new Adaptertransac(this, transactions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tranasc.tr.setLayoutManager(linearLayoutManager);
        tranasc.tr.setAdapter(adaptertransac);
        tranasc.nestedra.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener()
        {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    int Page= Integer.valueOf(page);
                    Page++;

             tranasc.idPBLoading.setVisibility(View.VISIBLE);
                      int total = Integer.valueOf(totalpage);

                    if (Page <= total-1)
                    {
                    //    Toast.makeText(TransactionDetails.this, "c:: "+count, Toast.LENGTH_SHORT).show();
                        start = String.valueOf(count);
                      //  Toast.makeText(TransactionDetails.this, "No More Report", Toast.LENGTH_SHORT).show();
                        try {
                            //Toast.makeText(TransactionDetails.this, "LEVEL 2 page "+page+"", Toast.LENGTH_SHORT).show();
                            int intPage= Integer.valueOf(page);
                            post(user_id, intPage);
                        }catch (Exception e)
                        {
                          //  Toast.makeText(TransactionDetails.this, "No More Report", Toast.LENGTH_SHORT).show();
                        }

                        // on below line we are again calling
                        // a method to load data in our array list.
                        //getData();

                    }

                    else
                    {
                        tranasc.idPBLoading.setVisibility(View.INVISIBLE);
                        Toast.makeText(TransactionDetails.this, "No More Report", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        tranasc.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(fp);
            }
        });

    }


}