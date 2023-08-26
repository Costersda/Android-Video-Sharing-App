package com.example.videosharingapp.model.ApiModels.videos;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Snippet {

    @SerializedName("publishedAt")
    Date publishedAt;

    @SerializedName("channelId")
    String channelId;

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("thumbnails")
    Thumbnails thumbnails;

    @SerializedName("channelTitle")
    String channelTitle;

    @SerializedName("liveBroadcastContent")
    String liveBroadcastContent;

    @SerializedName("publishTime")
    Date publishTime;


    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getChannelId() {
        return channelId;
    }

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

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }
    public String getChannelTitle() {
        return channelTitle;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }
    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    public Date getPublishTime() {
        return publishTime;
    }

}