package com.example.videosharingapp.model.ApiModels.videos;

import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("kind")
    String kind;

    @SerializedName("videoId")
    String videoId;


    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getKind() {
        return kind;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
    public String getVideoId() {
        return videoId;
    }

}