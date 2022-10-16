package com.teraninja.guard.Model;

import java.util.ArrayList;

public class ModelTypeCompany {
    public int status;
    public int code;
    public String message;
    public ArrayList<ItemTypeCompany> data;

    public ModelTypeCompany(int status, int code, String message, ArrayList<ItemTypeCompany> data) {
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

    public ArrayList<ItemTypeCompany> getData() {
        return data;
    }

    public void setData(ArrayList<ItemTypeCompany> data) {
        this.data = data;
    }
}
