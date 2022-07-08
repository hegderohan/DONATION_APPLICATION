package com.example.donation1;

public class Cause_details {

    String upi_id,cause,amount;

    Cause_details()
    {

    }
    public Cause_details(String upi_id, String cause, String amount) {
        this.upi_id = upi_id;
        this.cause = cause;
        this.amount = amount;
    }

    public String getUpi_id() {
        return upi_id;
    }

    public String getCause() {
        return cause;
    }

    public String getAmount() {
        return amount;
    }

    public void setUpi_id(String upi_id) {
        this.upi_id = upi_id;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
