package com.teraninja.guard.Model;

public class DataRecuestCode {
    public int status;
    public int code;
    public String message;
    public int data;

    public DataRecuestCode(int status, int code, String message, int data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
