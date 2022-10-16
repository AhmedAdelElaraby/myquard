package com.teraninja.guard.Model;

public class Notifications {
    public int id;
    public int user_id;
    public int notification_id;
    public int is_read;
    public String created_at;
    public DataOllNotification notification;

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

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public DataOllNotification getNotification() {
        return notification;
    }

    public void setNotification(DataOllNotification notification) {
        this.notification = notification;
    }
}
