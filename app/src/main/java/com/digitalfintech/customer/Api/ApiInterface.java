package com.digitalfintech.customer.Api;

import com.digitalfintech.customer.Modelclass.Firstpinenter;
import com.digitalfintech.customer.Modelclass.Forget_Pin;
import com.digitalfintech.customer.Modelclass.Loginenternumber;
import com.digitalfintech.customer.Modelclass.Updatepin;
import com.digitalfintech.customer.Modelclass.Verified_pin;
import com.digitalfintech.customer.Qrcoder.Qrmodel;
import com.digitalfintech.customer.Upipayment.Upipay;
import com.digitalfintech.customer.transactionst.TransacReport;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
//    mobile_number
//            type
//    login_with_pin
//            login_pin

//https://gocash.net.in/csp/netbanking_api/login_pin.php
// https://gocash.net.in/csp/netbanking_api/login.php

    @FormUrlEncoded
    @POST("login.php")
        // login url // Login
    Call<Loginenternumber> userlogincheck(@Field("mobile_number") String mobile_number, @Field("type") String type);

    @FormUrlEncoded
    @POST("login_pin.php")
        // login url  // login with pin
    Call<Firstpinenter> userpincheckfirst(@Field("mobile_number") String mobile_number, @Field("type") String type, @Field("login_pin") String login_pin);

    @FormUrlEncoded
    @POST("login_pin.php")
//https://gocash.net.in/csp/netbanking_api/login_pin.php // setpin
    Call<Updatepin> updatepin(@Field("mobile_number") String mobile_number, @Field("type") String type, @Field("login_pin") String login_pin);

    // Forget pin https://gocash.net.in/csp/netbanking_api/login.php
    @FormUrlEncoded
    @POST("login.php")
//https://gocash.net.in/csp/netbanking_api/login_pin.php // type forgetpin
    Call<Forget_Pin> forgetpin(@Field("mobile_number") String mobile_number, @Field("type") String type);

    @FormUrlEncoded
    @POST("transaction_report.php ")
//https://gocash.net.in/csp/netbanking_api/transaction_report.php // type transaction
    Call<TransacReport> transac(@Field("user_id") String user_id, @Field("start") String start);
//https://gocash.net.in/csp/netbanking_api/login.php

    @FormUrlEncoded
    @POST("login.php")
//verifypin
    Call<Verified_pin> verifypin(@Field("mobile_number") String mobile_number, @Field("type") String type, @Field("otp") String otp);

 ///////upipayment
    @FormUrlEncoded
    @POST("upi/upi_transfer.php")
    Call<Upipay> upipayment(@Field("user_id") String user_id, @Field("amount") int amount, @Field("beneficiary_name") String beneficiary_name, @Field("upi_id") String upi_id, @Field("note") String note);

 ///////wallet///////////////

    @FormUrlEncoded
    @POST("create_upi.php")
    Call<Qrmodel> qrcode (@Field("memberId") String memberId);
    @FormUrlEncoded
    @POST("wallet.php")
    Call<Qrmodel> wallet (@Field("mobile_number") String mobile_number,@Field("type")String type);



}
