package com.teraninja.guard.Model;

import java.util.ArrayList;

public class DataCvsguard {
    public int count;
    public ArrayList<DataGuardPayment> guards;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<DataGuardPayment> getGuards() {
        return guards;
    }

    public void setGuards(ArrayList<DataGuardPayment> guards) {
        this.guards = guards;
    }
}
