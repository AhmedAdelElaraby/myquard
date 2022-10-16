package com.teraninja.guard.Model;

import okhttp3.MultipartBody;

public class DataSendCompany {
    String name;
    String email;
    String phone;
    String password;
    String password_confirmation;
    String type;
    String commercial_registration_no;
    MultipartBody.Part commercial_registration_image;
    String company_type_id;
    String city_id;

    public DataSendCompany(String name, String email, String phone, String password, String password_confirmation, String type, String commercial_registration_no, MultipartBody.Part commercial_registration_image, String company_type_id, String city_id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.type = type;
        this.commercial_registration_no = commercial_registration_no;
        this.commercial_registration_image = commercial_registration_image;
        this.company_type_id = company_type_id;
        this.city_id = city_id;
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

    public String getCommercial_registration_no() {
        return commercial_registration_no;
    }

    public void setCommercial_registration_no(String commercial_registration_no) {
        this.commercial_registration_no = commercial_registration_no;
    }

    public MultipartBody.Part getCommercial_registration_image() {
        return commercial_registration_image;
    }

    public void setCommercial_registration_image(MultipartBody.Part commercial_registration_image) {
        this.commercial_registration_image = commercial_registration_image;
    }

    public String getCompany_type_id() {
        return company_type_id;
    }

    public void setCompany_type_id(String company_type_id) {
        this.company_type_id = company_type_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
