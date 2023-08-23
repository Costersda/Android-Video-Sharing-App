package com.example.videosharingapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Videos")
public class VideoInfo implements Serializable {

    @Ignore
    public static final String VIDEO_ID_KEY = "VIDEO_ID";
    @Ignore
    public static final String VIDEO_TITLE = "VIDEO_TITLE";
    @Ignore
    public static final String VIDEO_VIEW_COUNT = "VIDEO_VIEW_COUNT";
    @Ignore
    public static final String VIDEO_URL = "VIDEO_URL";

    @PrimaryKey
    @NonNull
    private String videoID;
    private String videoTitle;
    private String videoViewCount;
    private String videoUrl;

    //Constructors
    public VideoInfo(){}

    public VideoInfo(@NonNull String videoID, String videoTitle, String videoViewCount, String videoUrl) {
        this.videoID = videoID;
        this.videoTitle = videoTitle;
        this.videoViewCount = videoViewCount;
        this.videoUrl = videoUrl;
    }

    //Setters
    public void setVideoID(@NonNull String videoID) {
        this.videoID = videoID;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoViewCount(String videoViewCount) {
        this.videoViewCount = videoViewCount;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    //Getters
    @NonNull
    public String getVideoID() {
        return videoID;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoViewCount() {
        return videoViewCount;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
