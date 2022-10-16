package com.teraninja.guard.Model;

public class DataPaymentCompany {
    public int status;
    public int code;
    public String message;
    public DatapaymentCompanyurl data;

    public DataPaymentCompany(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DatapaymentCompanyurl getData() {
        return data;
    }

    public void setData(DatapaymentCompanyurl data) {
        this.data = data;
    }
}
