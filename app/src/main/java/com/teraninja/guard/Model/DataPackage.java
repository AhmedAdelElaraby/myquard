package com.teraninja.guard.Model;

import java.util.ArrayList;

public class DataPackage {
    public int status;
    public int code;
    public String message;
    public ArrayList<Package_Data> data;

    public DataPackage(int status, int code, String message, ArrayList<Package_Data> data) {
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

    public ArrayList<Package_Data> getData() {
        return data;
    }

    public void setData(ArrayList<Package_Data> data) {
        this.data = data;
    }
}
