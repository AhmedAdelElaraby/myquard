package com.teraninja.guard.Model;

import java.util.ArrayList;

public class SendDataOfferJob {
    String jop_type_id;
    String city_id;
    String district_id;
    String salary;
    String no_of_days;
    String no_of_hours;
    String last_date_to_accept;
    ArrayList<String> users;
    String holiday;
    String work_nature_id;
    String work_nature_text;

    public String getJop_type_id() {
        return jop_type_id;
    }

    public void setJop_type_id(String jop_type_id) {
        this.jop_type_id = jop_type_id;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(String no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getNo_of_hours() {
        return no_of_hours;
    }

    public void setNo_of_hours(String no_of_hours) {
        this.no_of_hours = no_of_hours;
    }

    public String getLast_date_to_accept() {
        return last_date_to_accept;
    }

    public void setLast_date_to_accept(String last_date_to_accept) {
        this.last_date_to_accept = last_date_to_accept;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getWork_nature_id() {
        return work_nature_id;
    }

    public void setWork_nature_id(String work_nature_id) {
        this.work_nature_id = work_nature_id;
    }

    public String getWork_nature_text() {
        return work_nature_text;
    }

    public void setWork_nature_text(String work_nature_text) {
        this.work_nature_text = work_nature_text;
    }
}
