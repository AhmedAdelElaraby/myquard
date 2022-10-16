package com.teraninja.guard.Model;

public class DataSendLogin {
    String phone;
    String password;
    String fcm_token;

    public DataSendLogin(String phone, String password, String fcm_token) {
        this.phone = phone;
        this.password = password;
        this.fcm_token = fcm_token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }
}
