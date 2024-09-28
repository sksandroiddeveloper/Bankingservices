package com.digitalfintech.customer.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.digitalfintech.customer.Api.ApiClient;
import com.digitalfintech.customer.Api.ApiInterface;
import com.digitalfintech.customer.Dashboard;
import com.digitalfintech.customer.Qrcoder.Qrmodel;
import com.digitalfintech.customer.ScanandPay.Sacnandpay;
import com.digitalfintech.customer.Upipayment.UpiPayment;
import com.digitalfintech.customer.Upipayment.Upipay;
import com.digitalfintech.customer.Webwiew;
import com.digitalfintech.customer.databinding.FragmentHomeBinding;
import com.digitalfintech.customer.transactionst.TransacReport;
import com.digitalfintech.customer.transactionst.TransactionDetails;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ShimmerFrameLayout container;

    private FragmentHomeBinding binding;
    String flight = "https://www.makemytrip.com/";
    String bike = "https://www.insurancedekho.com/bike-insurance";
    String car = " https://www.insurancedekho.com/car-insurance";
    String health = "  https://www.insurancedekho.com/health-insurance";
    String oyo = "https://www.oyorooms.com/";
    String ola = " https://book.olacabs.com/";
    String hotel = " https://www.booking.com/city/in/noida.html";
    String bus = "https://www.zingbus.com/";
    String zomato = "https://www.zomato.com/";
    String movie = "https://in.bookmyshow.com/";
    String train = "https://irctc.co.in/";
    String mobile_number, type;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("myKey", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("accountnumber", "");
        binding.account.setText("" + value);
        String balnace = sharedPreferences.getString("accountbalnace", "");
        binding.blanceview.setText("Rs " + balnace);

        String mobilenumber = sharedPreferences.getString("mobilenumber", "");
        binding.CRNo.setText("CRN No:  GCASH" + mobilenumber);
        String Ifsccode = sharedPreferences.getString("ifccode", "");
        binding.ifsccode.setText("IFSC Code: " + Ifsccode);
        // binding.shimmerViewContainer.startShimmer();


//        SharedPreferences sharedPreference = this.getActivity().getSharedPreferences("amount", Context.MODE_PRIVATE);
//        String balnaces = sharedPreference.getString("amount", "");

        //   data(mobile_number,type);
        binding.viewbalna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.viewbalna.setVisibility(View.GONE);
                binding.loadingPanel.setVisibility(View.VISIBLE);//  binding.progressh.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        binding.loadingPanel.setVisibility(View.INVISIBLE);//  binding.progressh.setVisibility(View.INVISIBLE);
                        data(mobile_number, type);
                         binding.blanceview.setVisibility(View.VISIBLE);

                    }
                }, 3000);


            }
        });
        binding.bankreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity().getApplicationContext(), TransactionDetails.class);
                getActivity().startActivity(myIntent);
            }
        });

        binding.flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", flight);
                startActivity(myIntent);
            }
        });
        binding.bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", bus);
                startActivity(myIntent);
            }
        });
        binding.health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", health);
                startActivity(myIntent);
            }
        });
        binding.ola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", ola);
                startActivity(myIntent);
            }
        });
        binding.hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", hotel);
                startActivity(myIntent);
            }
        });
        binding.zomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", zomato);
                startActivity(myIntent);
            }
        });
        binding.movieticiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", movie);
                startActivity(myIntent);
            }
        });

        binding.irtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Webwiew.class);
                myIntent.putExtra("web", train);
                startActivity(myIntent);
            }
        });
        binding.scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), Sacnandpay.class);
                startActivity(myIntent);
            }


        });


        binding.toupiid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), UpiPayment.class);
                myIntent.putExtra("web", train);
                startActivity(myIntent);
            }
        });

        return root;
    }

    private void data(String mobile_number, String type) {

        SharedPreferences sh = this.getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        mobile_number = sh.getString("mobile_numbes", "");
        type = "fetch";
        ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<Qrmodel> call = apiInterface.wallet(mobile_number, type);
        call.enqueue(new Callback<Qrmodel>() {

            @Override

            public void onResponse(Call<Qrmodel> call, Response<Qrmodel> response) {

                if (response.body().getStatus_message().equalsIgnoreCase("Account balance found")) {

                    String amount = response.body().getData().getAccount_balance();
                    binding.blanceview.setText("Rs " + amount);

                } else {

//                    progressBar.dismiss();
                    Toast.makeText(getActivity(), "Something went Wrong Try Again Later !! ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Qrmodel> call, Throwable t) {
                binding.loadingPanel.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                progressBar.dismiss();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
//    // Hide status bar
//    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//// Show status bar
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);