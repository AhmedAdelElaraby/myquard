package com.teraninja.guard.Model;

import java.util.ArrayList;

public class DataNotifications {
    public int status;
    public int code;
    public String message;
    public ArrayList<Notifications> data;

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

    public ArrayList<Notifications> getData() {
        return data;
    }

    public void setData(ArrayList<Notifications> data) {
        this.data = data;
    }
}
