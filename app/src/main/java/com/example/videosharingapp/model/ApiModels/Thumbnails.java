package com.example.videosharingapp.model.ApiModels;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("default")
    Default deFault;

    @SerializedName("medium")
    Medium medium;

    @SerializedName("high")
    High high;


    public void setDefault(Default deFault) {
        this.deFault = deFault;
    }
    public Default getDefault() {
        return deFault;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }
    public Medium getMedium() {
        return medium;
    }

    public void setHigh(High high) {
        this.high = high;
    }
    public High getHigh() {
        return high;
    }

}