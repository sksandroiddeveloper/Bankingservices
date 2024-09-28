package com.digitalfintech.customer.Modelclass;

public class Updatepin {

//      "status_code": 200,
//              "status": "pin_updated",
//              "status_message": "PIN Updated Successfully",
//


       String status_code;
       String status;
       String status_message;

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
