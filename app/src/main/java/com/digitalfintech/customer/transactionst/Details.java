package com.digitalfintech.customer.transactionst;

public class Details {

//      "id": "6",
//              "bene_account_no": "2223330083932488",
//              "bene_account_ifsc": "RATN0VAAPIS",
//              "rmtr_full_name": "SHARVAN KUMAR",
//              "rmtr_account_no": "502000487150",
//              "rmtr_account_ifsc": "NSPB0000002",
//              "rmtr_to_bene_note": "",
//              "amount": "100",
//              "settled_amount": "100",
//              "created_at": "2023-01-21 05:49:36",
//              "payment_type": "UPI",
//              "bank_ref_num": "302122075850",
//              "hypto_va_id": "va_L0SCkmwoKSiMkz"

String id;
String bene_account_no;
String bene_account_ifsc;
String  rmtr_full_name;
String rmtr_account_no;
String rmtr_account_ifsc;
String  rmtr_to_bene_note;
String  amount;
String settled_amount;
String created_at;
String payment_type;
String bank_ref_num;
String  hypto_va_id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBene_account_no() {
        return bene_account_no;
    }

    public void setBene_account_no(String bene_account_no) {
        this.bene_account_no = bene_account_no;
    }

    public String getBene_account_ifsc() {
        return bene_account_ifsc;
    }

    public void setBene_account_ifsc(String bene_account_ifsc) {
        this.bene_account_ifsc = bene_account_ifsc;
    }

    public String getRmtr_full_name() {
        return rmtr_full_name;
    }

    public void setRmtr_full_name(String rmtr_full_name) {
        this.rmtr_full_name = rmtr_full_name;
    }

    public String getRmtr_account_no() {
        return rmtr_account_no;
    }

    public void setRmtr_account_no(String rmtr_account_no) {
        this.rmtr_account_no = rmtr_account_no;
    }

    public String getRmtr_account_ifsc() {
        return rmtr_account_ifsc;
    }

    public void setRmtr_account_ifsc(String rmtr_account_ifsc) {
        this.rmtr_account_ifsc = rmtr_account_ifsc;
    }

    public String getRmtr_to_bene_note() {
        return rmtr_to_bene_note;
    }

    public void setRmtr_to_bene_note(String rmtr_to_bene_note) {
        this.rmtr_to_bene_note = rmtr_to_bene_note;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSettled_amount() {
        return settled_amount;
    }

    public void setSettled_amount(String settled_amount) {
        this.settled_amount = settled_amount;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getBank_ref_num() {
        return bank_ref_num;
    }

    public void setBank_ref_num(String bank_ref_num) {
        this.bank_ref_num = bank_ref_num;
    }

    public String getHypto_va_id() {
        return hypto_va_id;
    }

    public void setHypto_va_id(String hypto_va_id) {
        this.hypto_va_id = hypto_va_id;
    }
}
