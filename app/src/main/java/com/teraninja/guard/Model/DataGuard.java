package com.teraninja.guard.Model;

public class DataGuard {
    public int id;
    public String name;
    public String image;
    public String updated_at;
    public int appear;
    public boolean cheek;

    public boolean isCheek() {
        return cheek;
    }

    public void setCheek(boolean cheek) {
        this.cheek = cheek;
    }

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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getAppear() {
        return appear;
    }

    public void setAppear(int appear) {
        this.appear = appear;
    }
}
