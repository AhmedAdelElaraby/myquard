package com.teraninja.guard.Model;

import java.util.ArrayList;

public class SliderNews {
    public String about_us;
    public ArrayList<HomeImage> home_images;
    public ArrayList<News> news;

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public ArrayList<HomeImage> getHome_images() {
        return home_images;
    }

    public void setHome_images(ArrayList<HomeImage> home_images) {
        this.home_images = home_images;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }
}
