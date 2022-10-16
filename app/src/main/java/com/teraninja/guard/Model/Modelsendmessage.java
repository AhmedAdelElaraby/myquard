package com.teraninja.guard.Model;

public class Modelsendmessage {
    public int status;
    public int code;
    public String message;
    public ModelMessage data;

    public Modelsendmessage(int status, int code, String message) {
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

    public ModelMessage getData() {
        return data;
    }

    public void setData(ModelMessage data) {
        this.data = data;
    }
}
