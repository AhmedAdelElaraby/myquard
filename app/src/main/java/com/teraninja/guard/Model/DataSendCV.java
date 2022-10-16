package com.teraninja.guard.Model;

import okhttp3.MultipartBody;

public class DataSendCV {
    String jop_type_id;
    String name;
    String email;
    String age;
    String gender;
    MultipartBody.Part image;
    String city_id;
    String district_id;
    MultipartBody.Part identification_id;
    String iban_no;
    String experience;
    String qualification;
    String experience_type;
    String social_status;
    String other_cities;
    String english;



    public String getJop_type_id() {
        return jop_type_id;
    }

    public void setJop_type_id(String jop_type_id) {
        this.jop_type_id = jop_type_id;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public MultipartBody.Part getIdentification_id() {
        return identification_id;
    }

    public void setIdentification_id(MultipartBody.Part identification_id) {
        this.identification_id = identification_id;
    }

    public String getIban_no() {
        return iban_no;
    }

    public void setIban_no(String iban_no) {
        this.iban_no = iban_no;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience_type() {
        return experience_type;
    }

    public void setExperience_type(String experience_type) {
        this.experience_type = experience_type;
    }

    public String getSocial_status() {
        return social_status;
    }

    public void setSocial_status(String social_status) {
        this.social_status = social_status;
    }

    public String getOther_cities() {
        return other_cities;
    }

    public void setOther_cities(String other_cities) {
        this.other_cities = other_cities;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
