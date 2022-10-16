package com.teraninja.guard.Model;

import okhttp3.MultipartBody;

public class SendDataUpdateProfileCompany {
    String name;
    String email;
    String company_type_id;
    String commercial_registration_no;
    String type;
    String city_id;
    MultipartBody.Part image;

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
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

    public String getCompany_type_id() {
        return company_type_id;
    }

    public void setCompany_type_id(String company_type_id) {
        this.company_type_id = company_type_id;
    }

    public String getCommercial_registration_no() {
        return commercial_registration_no;
    }

    public void setCommercial_registration_no(String commercial_registration_no) {
        this.commercial_registration_no = commercial_registration_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
