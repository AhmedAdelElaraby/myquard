package com.teraninja.guard.Model;

public class Profile {
    public int status;
    public int code;
    public String message;
    public DataProfileHome data;

    public Profile(int status, int code, String message) {
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

    public DataProfileHome getData() {
        return data;
    }

    public void setData(DataProfileHome data) {
        this.data = data;
    }
}
