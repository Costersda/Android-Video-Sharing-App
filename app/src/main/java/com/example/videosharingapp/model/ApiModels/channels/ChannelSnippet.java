package com.example.videosharingapp.model.ApiModels.channels;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ChannelSnippet {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("customUrl")
    String customUrl;

    @SerializedName("publishedAt")
    Date publishedAt;

    @SerializedName("thumbnails")
    ChannelThumbnails channelThumbnails;

    @SerializedName("localized")
    ChannelLocalized channelLocalized;

    @SerializedName("country")
    String country;


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

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }
    public String getCustomUrl() {
        return customUrl;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setThumbnails(ChannelThumbnails channelThumbnails) {
        this.channelThumbnails = channelThumbnails;
    }
    public ChannelThumbnails getThumbnails() {
        return channelThumbnails;
    }

    public void setLocalized(ChannelLocalized channelLocalized) {
        this.channelLocalized = channelLocalized;
    }
    public ChannelLocalized getLocalized() {
        return channelLocalized;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

}