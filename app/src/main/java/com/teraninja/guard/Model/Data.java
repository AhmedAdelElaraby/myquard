package com.teraninja.guard.Model;

public class Data {
    public String token;
    public DataProfile user;

    public Data(String token, DataProfile user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataProfile getUser() {
        return user;
    }

    public void setUser(DataProfile user) {
        this.user = user;
    }
}
