package com.teraninja.guard.Model;

public class DatauserOffer {
    public int id;
    public int user_id;
    public int city_id;
    public int district_id;
    public int jop_type_id;
    public int salary;
    public String holiday;
    public int no_of_days;
    public int no_of_hours;
    public String last_date_to_accept;
    public Object work_nature_text;
    public String created_at;
    public int work_nature_id;
    public int job_users_count;
    public City city;
    public JopType jop_type;
    public District district;
    public WorkNature work_nature;
    public Companyoffers company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public int getJop_type_id() {
        return jop_type_id;
    }

    public void setJop_type_id(int jop_type_id) {
        this.jop_type_id = jop_type_id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public int getNo_of_hours() {
        return no_of_hours;
    }

    public void setNo_of_hours(int no_of_hours) {
        this.no_of_hours = no_of_hours;
    }

    public String getLast_date_to_accept() {
        return last_date_to_accept;
    }

    public void setLast_date_to_accept(String last_date_to_accept) {
        this.last_date_to_accept = last_date_to_accept;
    }

    public Object getWork_nature_text() {
        return work_nature_text;
    }

    public void setWork_nature_text(Object work_nature_text) {
        this.work_nature_text = work_nature_text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getWork_nature_id() {
        return work_nature_id;
    }

    public void setWork_nature_id(int work_nature_id) {
        this.work_nature_id = work_nature_id;
    }

    public int getJob_users_count() {
        return job_users_count;
    }

    public void setJob_users_count(int job_users_count) {
        this.job_users_count = job_users_count;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public JopType getJop_type() {
        return jop_type;
    }

    public void setJop_type(JopType jop_type) {
        this.jop_type = jop_type;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public WorkNature getWork_nature() {
        return work_nature;
    }

    public void setWork_nature(WorkNature work_nature) {
        this.work_nature = work_nature;
    }

    public Companyoffers getCompany() {
        return company;
    }

    public void setCompany(Companyoffers company) {
        this.company = company;
    }
}
