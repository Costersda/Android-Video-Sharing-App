package com.example.videosharingapp.model.ApiModels;

import com.google.gson.annotations.SerializedName;

public class High {

    @SerializedName("url")
    String url;

    @SerializedName("width")
    int width;

    @SerializedName("height")
    int height;


    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }

}