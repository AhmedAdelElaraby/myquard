package com.teraninja.guard.Model;

public class SendNewPassWoed {
    String phone;
    String code;
    String password;
    String password_confirmation;



    public SendNewPassWoed(String phone, String code, String password, String password_confirmation) {
        this.phone = phone;
        this.code = code;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}
