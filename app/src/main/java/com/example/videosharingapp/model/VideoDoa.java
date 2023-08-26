package com.example.videosharingapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VideoDoa {
    @Query("SELECT * FROM Videos")
    LiveData<List<VideoInfo>> getVideoInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertVideoInfo(VideoInfo videoInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllVideos(List<VideoInfo> videoInfoList);




}
