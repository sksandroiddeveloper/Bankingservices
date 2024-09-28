package com.digitalfintech.customer.Modelclass;

public class user_details {

    //   "id": "152",
//           "csp_id_fk": "94",
//           "agent_id_fk": "30",
//           "member_name": "sunil kumar",
//           "member_father_name": "ram shanakr",
//           "mobile_number": "9205103519",
//           "email": "SUNIL.KUMAR1983FTP@GMAIL.COM",
//           "aadhaar_number": "987918798713",
//           "pan_card": "CBVPG1750I",
//           "address": "36 B BLOCK",
//           "date_of_birth": "101987-10-06",
//           "pin_code": "110018",
//           "account_status": "active",
//           "bank_name": "rbl_bank",
//           "account_balance": "6200",
//           "account_points": "0",
//           "credit_limit": "0.00",
//           "login_pin": "1234",
//           "otp": null,
//           "upi_limit_crossed": "0",
//           "upi_limit_perday": "5000",
//           "created_on": "Nov,18,2022 7:18 PM"
    String account_balance ;
    String id;
    String email;
    String mobile_number;
    String member_name;
    String member_father_name;
    String address;
    String profile_pic;
    String login_pin;

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobile_number;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobile_number = mobilenumber;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;

    }

    public String getMember_father_name() {
        return member_father_name;
    }

    public void setMember_father_name(String member_father_name) {
        this.member_father_name = member_father_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin_pin() {
        return login_pin;
    }

    public void setLogin_pin(String login_pin) {
        this.login_pin = login_pin;
    }
}




