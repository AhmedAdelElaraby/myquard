package com.teraninja.guard.Model;

public class Package_Data {
    public int id;
    public int no_of_days;
    public int price;
    public Object discount;
    public String title;
    public String description;

    public Package_Data(int id, int no_of_days, int price, Object discount, String title, String description) {
        this.id = id;
        this.no_of_days = no_of_days;
        this.price = price;
        this.discount = discount;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
