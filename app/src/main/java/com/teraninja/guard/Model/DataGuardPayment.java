package com.teraninja.guard.Model;

public class DataGuardPayment {
    public int id;
    public int company_id;
    public int guard_id;
    public int the_number_of_days_left;
    public CompanyGuard company_guard;
    public boolean cheek;

    public boolean isCheek() {
        return cheek;
    }

    public void setCheek(boolean cheek) {
        this.cheek = cheek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getGuard_id() {
        return guard_id;
    }

    public void setGuard_id(int guard_id) {
        this.guard_id = guard_id;
    }

    public int getThe_number_of_days_left() {
        return the_number_of_days_left;
    }

    public void setThe_number_of_days_left(int the_number_of_days_left) {
        this.the_number_of_days_left = the_number_of_days_left;
    }

    public CompanyGuard getCompany_guard() {
        return company_guard;
    }

    public void setCompany_guard(CompanyGuard company_guard) {
        this.company_guard = company_guard;
    }
}
