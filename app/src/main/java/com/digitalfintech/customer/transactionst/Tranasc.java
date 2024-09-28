package com.digitalfintech.customer.transactionst;

public class Tranasc {


    String id;
    String nb_id_fk;
    String csp_id_fk;
    String type;
    String txn_id;
    String amount;
    String status;
    String purpose;
    String note;
    String bene_upi_id;
    String created_on;
    private Details details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNb_id_fk() {
        return nb_id_fk;
    }

    public void setNb_id_fk(String nb_id_fk) {
        this.nb_id_fk = nb_id_fk;
    }

    public String getCsp_id_fk() {
        return csp_id_fk;
    }

    public void setCsp_id_fk(String csp_id_fk) {
        this.csp_id_fk = csp_id_fk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBene_upi_id() {
        return bene_upi_id;
    }

    public void setBene_upi_id(String bene_upi_id) {
        this.bene_upi_id = bene_upi_id;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public Details getDetails()
    {
        return details;
    }

    public void setDetails(Details details)
    {
        this.details = details;
    }
}