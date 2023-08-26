package com.example.videosharingapp.model.ApiModels.channels;

import com.google.gson.annotations.SerializedName;

public class ChannelStatistics {

    @SerializedName("viewCount")
    String viewCount;

    @SerializedName("subscriberCount")
    String subscriberCount;

    @SerializedName("hiddenSubscriberCount")
    boolean hiddenSubscriberCount;

    @SerializedName("videoCount")
    String videoCount;


    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }
    public String getViewCount() {
        return viewCount;
    }

    public void setSubscriberCount(String subscriberCount) {
        this.subscriberCount = subscriberCount;
    }
    public String getSubscriberCount() {
        return subscriberCount;
    }

    public void setHiddenSubscriberCount(boolean hiddenSubscriberCount) {
        this.hiddenSubscriberCount = hiddenSubscriberCount;
    }
    public boolean getHiddenSubscriberCount() {
        return hiddenSubscriberCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
    }
    public String getVideoCount() {
        return videoCount;
    }

}