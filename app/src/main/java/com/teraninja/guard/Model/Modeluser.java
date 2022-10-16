package com.teraninja.guard.Model;

public class Modeluser {
    public int id;
    public String name;
    public String updated_at;
    public String image;
    public int theNumberOfDaysLeft;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTheNumberOfDaysLeft() {
        return theNumberOfDaysLeft;
    }

    public void setTheNumberOfDaysLeft(int theNumberOfDaysLeft) {
        this.theNumberOfDaysLeft = theNumberOfDaysLeft;
    }
}
