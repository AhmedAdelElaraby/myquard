package com.teraninja.guard.Model;

public class Joboffer {
    private int id;
    private int userId;
    private int cityId;
    private int districtId;
    private int jopTypeId;
    private int salary;
    private String holiday;
    private int noOfDays;
    private int no_of_hours;
    private String last_date_to_accept;
    private String workNatureText;
    private String created_at;
    private int workNatureId;
    private City city;
    private JopType jop_type;
    private District district;
    private WorkNature workNature;
    private Companyoffers company;
    private Object holidays ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getJopTypeId() {
        return jopTypeId;
    }

    public void setJopTypeId(int jopTypeId) {
        this.jopTypeId = jopTypeId;
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

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
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

    public String getWorkNatureText() {
        return workNatureText;
    }

    public void setWorkNatureText(String workNatureText) {
        this.workNatureText = workNatureText;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getWorkNatureId() {
        return workNatureId;
    }

    public void setWorkNatureId(int workNatureId) {
        this.workNatureId = workNatureId;
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

    public WorkNature getWorkNature() {
        return workNature;
    }

    public void setWorkNature(WorkNature workNature) {
        this.workNature = workNature;
    }

    public Companyoffers getCompany() {
        return company;
    }

    public void setCompany(Companyoffers company) {
        this.company = company;
    }

    public Object getHolidays() {
        return holidays;
    }

    public void setHolidays(Object holidays) {
        this.holidays = holidays;
    }
}
