package com.example.videosharingapp.model.ApiModels.channels;

import com.google.gson.annotations.SerializedName;

public class ChannelItems {

    @SerializedName("kind")
    String kind;

    @SerializedName("etag")
    String etag;

    @SerializedName("id")
    String id;

    @SerializedName("snippet")
    ChannelSnippet channelSnippet;

    @SerializedName("statistics")
    ChannelStatistics channelStatistics;


    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getKind() {
        return kind;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
    public String getEtag() {
        return etag;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setSnippet(ChannelSnippet channelSnippet) {
        this.channelSnippet = channelSnippet;
    }
    public ChannelSnippet getSnippet() {
        return channelSnippet;
    }

    public void setStatistics(ChannelStatistics channelStatistics) {
        this.channelStatistics = channelStatistics;
    }
    public ChannelStatistics getStatistics() {
        return channelStatistics;
    }

}