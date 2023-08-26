package com.example.videosharingapp.model.ApiModels.channels;

import com.google.gson.annotations.SerializedName;


public class ChannelModel {
    @SerializedName("kind")
    String kind;

    @SerializedName("etag")
    String etag;

    @SerializedName("pageInfo")
    ChannelPageInfo channelPageInfo;

    @SerializedName("items")
    ChannelItems[] items;


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

    public void setPageInfo(ChannelPageInfo channelPageInfo) {
        this.channelPageInfo = channelPageInfo;
    }
    public ChannelPageInfo getPageInfo() {
        return channelPageInfo;
    }

    public void setItems(ChannelItems[] items) {
        this.items = items;
    }
    public ChannelItems[] getItems() {
        return items;
    }

}