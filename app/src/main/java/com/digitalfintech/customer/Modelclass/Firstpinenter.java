package com.digitalfintech.customer.Modelclass;

public class Firstpinenter {

//    "status_code": 400,
//            "status": "incorrect_pin",
//            "status_message": "Incorrect Login PIN",


    String status_code;
    String  status;
    String status_message;
    private data data;



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

    public com.digitalfintech.customer.Modelclass.data getData() {
        return data;
    }

    public void setData(com.digitalfintech.customer.Modelclass.data data) {
        this.data = data;
    }


}
