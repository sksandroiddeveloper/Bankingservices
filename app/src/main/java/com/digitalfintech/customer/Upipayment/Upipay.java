package com.digitalfintech.customer.Upipayment;

public class Upipay {


    int status_code;
    String status, status_message, upi_id, beneficiary_name, reference_id, utr_no;
    Upipay data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

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

    public String getBeneficiary_name() {
        return beneficiary_name;
    }

    public void setBeneficiary_name(String beneficiary_name) {
        this.beneficiary_name = beneficiary_name;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public String getUtr_no() {
        return utr_no;
    }

    public void setUtr_no(String utr_no) {
        this.utr_no = utr_no;
    }

    public Upipay getData() {
        return data;
    }

    public void setData(Upipay data) {
        this.data = data;
    }
}
