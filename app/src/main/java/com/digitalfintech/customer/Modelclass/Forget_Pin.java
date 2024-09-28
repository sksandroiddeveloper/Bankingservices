package com.digitalfintech.customer.Modelclass;
//"status_code": 200,
//        "status": "success",
//        "status_message": "OTP Sent Successfully.",
//        "data": null
public class Forget_Pin {

String status_code ,status , status_message;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
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
}
