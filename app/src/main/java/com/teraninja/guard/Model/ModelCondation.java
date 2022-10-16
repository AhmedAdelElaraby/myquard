package com.teraninja.guard.Model;

import java.util.ArrayList;

public class ModelCondation {
    public int status;
    public int code;
    public String message;
    public ArrayList<DataItem> data;

    public ModelCondation(int status, int code, String message, ArrayList<DataItem> data) {
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

    public ArrayList<DataItem> getData() {
        return data;
    }

    public void setData(ArrayList<DataItem> data) {
        this.data = data;
    }
}
