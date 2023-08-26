package com.example.videosharingapp.model.ApiModels.channels;

import com.google.gson.annotations.SerializedName;

public class ChannelThumbnails {

    @SerializedName("default")
    ChannelDefault deFault;

    @SerializedName("medium")
    ChannelMedium channelMedium;

    @SerializedName("high")
    ChannelHigh channelHigh;


    public void setDefault(ChannelDefault deFault) {
        this.deFault = deFault;
    }
    public ChannelDefault getDefault() {
        return deFault;
    }

    public void setMedium(ChannelMedium channelMedium) {
        this.channelMedium = channelMedium;
    }
    public ChannelMedium getMedium() {
        return channelMedium;
    }

    public void setHigh(ChannelHigh channelHigh) {
        this.channelHigh = channelHigh;
    }
    public ChannelHigh getHigh() {
        return channelHigh;
    }

}