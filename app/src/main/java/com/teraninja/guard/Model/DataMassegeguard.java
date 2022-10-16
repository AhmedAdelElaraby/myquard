package com.teraninja.guard.Model;

public class DataMassegeguard {
    private int id;
    private String name;
    private String image;
    private LastMessege lastMessage;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LastMessege getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(LastMessege lastMessage) {
        this.lastMessage = lastMessage;
    }
}
