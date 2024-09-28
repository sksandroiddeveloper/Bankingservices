package com.digitalfintech.customer.Modelclass;

public class data {
    private virtual_account virtual_account;
    private user_details user_details;
    String account_balance;

    public com.digitalfintech.customer.Modelclass.virtual_account getVirtual_account() {
        return virtual_account;
    }

    public void setVirtual_account(com.digitalfintech.customer.Modelclass.virtual_account virtual_account) {
        this.virtual_account = virtual_account;
    }

    public com.digitalfintech.customer.Modelclass.user_details getUser_details() {
        return user_details;
    }

    public void setUser_details(com.digitalfintech.customer.Modelclass.user_details user_details) {
        this.user_details = user_details;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }
}
