package com.example.videosharingapp.model.ApiModels.channels;

import com.google.gson.annotations.SerializedName;

public class ChannelLocalized {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;


    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

}