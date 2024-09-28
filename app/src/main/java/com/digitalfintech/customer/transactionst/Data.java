package com.digitalfintech.customer.transactionst;

import java.util.List;

public class Data
{

//     "total_records": 16,
////                "record_found": 2,


    String total_records;
    String  record_found;
    String next_page_start;
    List<Tranasc>transactions;

    public String getTotal_records()
    {
        return total_records;
    }

    public void setTotal_records(String total_records) {
        this.total_records = total_records;
    }

    public String getRecord_found() {
        return record_found;
    }

    public void setRecord_found(String record_found) {
        this.record_found = record_found;
    }

    public List<Tranasc> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Tranasc> transactions) {
        this.transactions = transactions;
    }

    public String getNext_page_start() {
        return next_page_start;
    }

    public void setNext_page_start(String next_page_start) {
        this.next_page_start = next_page_start;
    }
}
