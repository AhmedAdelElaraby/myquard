package com.teraninja.guard.Model;

public class DataSendRegister {
    String name;
    String email;
    String phone;
    String password;
    String password_confirmation;
    String type;


    public DataSendRegister() {
    }

    public DataSendRegister(String name, String email, String phone, String password, String password_confirmation, String type) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
