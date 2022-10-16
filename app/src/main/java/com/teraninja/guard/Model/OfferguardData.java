package com.teraninja.guard.Model;

public class OfferguardData {
    public int id;
    public int userId;
    public int jobId;
    public String status;
    public Joboffer job;

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

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Joboffer getJob() {
        return job;
    }

    public void setJob(Joboffer job) {
        this.job = job;
    }
}
