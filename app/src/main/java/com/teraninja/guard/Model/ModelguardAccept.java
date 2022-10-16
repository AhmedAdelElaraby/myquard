package com.teraninja.guard.Model;

public class ModelguardAccept {
    public int id;
    public int user_id;
    public int job_id;
    public String status;
    public Modeluser user;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Modeluser getUser() {
        return user;
    }

    public void setUser(Modeluser user) {
        this.user = user;
    }
}
