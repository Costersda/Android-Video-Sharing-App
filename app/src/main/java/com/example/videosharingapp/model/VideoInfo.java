package com.example.videosharingapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Videos")
public class VideoInfo implements Serializable {

    @Ignore
    public static final String VIDEO_TITLE = "VIDEO_TITLE";
    @Ignore
    public static final String VIDEO_THUMBNAIL_URL = "VIDEO_THUMBNAIL_URL";
    @Ignore
    public static final String VIDEO_URL = "VIDEO_URL";

    @PrimaryKey
    @NonNull
    private String videoUrl;
    private String videoTitle;
    private String videoThumbnailUrl;


    //Constructors
    public VideoInfo(){}

    public VideoInfo(@NonNull String videoUrl, String videoTitle, String videoThumbnailUrl) {
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
        this.videoThumbnailUrl = videoThumbnailUrl;
    }

    //Setters
    public void setVideoUrl(@NonNull String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoThumbnailUrl(String videoThumbnailUrl) {
        this.videoThumbnailUrl = videoThumbnailUrl;
    }

    //Getters
    @NonNull
    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoThumbnailUrl() {
        return videoThumbnailUrl;
    }
}
