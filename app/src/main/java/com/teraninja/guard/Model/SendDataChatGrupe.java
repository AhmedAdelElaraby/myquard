package com.teraninja.guard.Model;

import java.util.ArrayList;

public class SendDataChatGrupe {
    String jop_type_id;
    ArrayList<String> users;

    public String getJop_type_id() {
        return jop_type_id;
    }

    public void setJop_type_id(String jop_type_id) {
        this.jop_type_id = jop_type_id;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
