package com.teraninja.guard.Model;

import java.util.ArrayList;

public class DataCountStaff {
    public int count;
    public ArrayList<DataGuard> guards;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<DataGuard> getGuards() {
        return guards;
    }

    public void setGuards(ArrayList<DataGuard> guards) {
        this.guards = guards;
    }
}
