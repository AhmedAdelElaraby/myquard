package com.teraninja.guard.Model;

public class DataShowUserByUser {
    private Integer status;
    private Integer code;
    private String message;
    private DataProfileHome data;

    public DataShowUserByUser(Integer status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataProfileHome getData() {
        return data;
    }

    public void setData(DataProfileHome data) {
        this.data = data;
    }

}
