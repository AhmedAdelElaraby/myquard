package com.teraninja.guard.Model;

public class Image {
    public int id;
    public int imageable_id;
    public String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageable_id() {
        return imageable_id;
    }

    public void setImageable_id(int imageable_id) {
        this.imageable_id = imageable_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
