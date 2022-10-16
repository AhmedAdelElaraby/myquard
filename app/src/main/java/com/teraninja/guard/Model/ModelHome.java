package com.teraninja.guard.Model;

public class ModelHome {
    public int status;
    public int code;
    public String message;
    public SliderNews data;

    public ModelHome(int status, int code, String message, SliderNews data) {
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

    public SliderNews getData() {
        return data;
    }

    public void setData(SliderNews data) {
        this.data = data;
    }
}
