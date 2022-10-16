package com.teraninja.guard.Model;

public class DataItem {
    public int id;
    public int jop_type_id;
    public String condition;

    public DataItem(int id, int jop_type_id, String condition) {
        this.id = id;
        this.jop_type_id = jop_type_id;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJop_type_id() {
        return jop_type_id;
    }

    public void setJop_type_id(int jop_type_id) {
        this.jop_type_id = jop_type_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
