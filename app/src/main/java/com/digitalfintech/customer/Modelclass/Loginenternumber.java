package com.digitalfintech.customer.Modelclass;
public class Loginenternumber {


//     "status_code": 400,
//             "status": "pin_exist",
//             "status_message": "Login Using Pin",
//             "data": null

// "status_code": 200,
//         "status": "success",
//         "status_message": "PIN matched Successfully",
//         "data": {
//        "user_details": {
//            "id": "152",
//                    "csp_id_fk": "94",
//                    "agent_id_fk": "30",
//                    "member_name": "sunil kumar",
//                    "member_father_name": "ram shanakr",
//                    "mobile_number": "9205103519",
//                    "email": "SUNIL.KUMAR1983FTP@GMAIL.COM",
//                    "aadhaar_number": "987918798713",
//                    "pan_card": "CBVPG1750I",
//                    "address": "36 B BLOCK",
//                    "date_of_birth": "101987-10-06",
//                    "pin_code": "110018",
//                    "account_status": "active",
//                    "bank_name": "rbl_bank",
//                    "account_balance": "6200",
//                    "account_points": "0",
//                    "credit_limit": "0.00",
//                    "login_pin": "123456",
//                    "otp": null,
//                    "upi_limit_crossed": "0",
//                    "upi_limit_perday": "5000",
//                    "created_on": "Nov,18,2022 7:18 PM"
//        },
//        "virtual_account": {
//            "icici_bank": false,
//                    "yes_bank": false,
//                    "rbl_bank": {
//                "account_number": "2223330083932488",
//                        "account_ifsc": "RATN0VAAPIS",
//                        "payment_modes": [
//                "UPI",
//                        "IMPS",
//                        "NEFT",
//                        "RTGS"
//         ]
//            },
//            "kotak_bank": false,
//                    "dbs_bank": false
//        },
//        "upi_id": "upi_not_exist"
//    }
//
  String status_code;
   String  status;
    String  status_message;


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
