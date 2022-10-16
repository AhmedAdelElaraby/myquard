package com.teraninja.guard.Model;

public class DataTems {
    public int status;
    public int code;
    public String message;
    public Terms data;

    public DataTems(int status, int code, String message) {
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

    public Terms getData() {
        return data;
    }

    public void setData(Terms data) {
        this.data = data;
    }
}
