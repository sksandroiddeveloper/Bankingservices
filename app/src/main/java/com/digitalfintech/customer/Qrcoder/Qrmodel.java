package com.digitalfintech.customer.Qrcoder;
//"status_code": 400,
//        "status": "already_upi_user",
//        "status_message": "User Already Have a UPI ID",
public class Qrmodel {
    String status;
    String status_message,upi_id,account_balance;
    Qrmodel data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getUpi_id() {
        return upi_id;
    }

    public void setUpi_id(String upi_id) {
        this.upi_id = upi_id;
    }

    public Qrmodel getData() {
        return data;
    }

    public void setData(Qrmodel data) {
        this.data = data;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }
}
