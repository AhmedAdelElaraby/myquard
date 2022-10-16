package com.teraninja.guard.Model;

import java.util.ArrayList;

public class BodyMessage {
    public String date;
    public ArrayList<MessageShow> messages;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<MessageShow> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageShow> messages) {
        this.messages = messages;
    }
}
