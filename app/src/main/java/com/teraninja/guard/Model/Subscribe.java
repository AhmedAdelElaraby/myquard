package com.teraninja.guard.Model;

public class Subscribe {
    public int subscribe_package_id;
    public int theNumberOfDaysLeft;
    public String subscribe_package_title;
    public String subscribe_package_description;

    public int getSubscribe_package_id() {
        return subscribe_package_id;
    }

    public void setSubscribe_package_id(int subscribe_package_id) {
        this.subscribe_package_id = subscribe_package_id;
    }

    public int getTheNumberOfDaysLeft() {
        return theNumberOfDaysLeft;
    }

    public void setTheNumberOfDaysLeft(int theNumberOfDaysLeft) {
        this.theNumberOfDaysLeft = theNumberOfDaysLeft;
    }

    public String getSubscribe_package_title() {
        return subscribe_package_title;
    }

    public void setSubscribe_package_title(String subscribe_package_title) {
        this.subscribe_package_title = subscribe_package_title;
    }

    public String getSubscribe_package_description() {
        return subscribe_package_description;
    }

    public void setSubscribe_package_description(String subscribe_package_description) {
        this.subscribe_package_description = subscribe_package_description;
    }
}
