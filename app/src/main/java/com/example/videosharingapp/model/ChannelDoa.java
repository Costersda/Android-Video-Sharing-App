package com.example.videosharingapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChannelDoa {
    @Query("SELECT * FROM Channels")
    LiveData<List<ChannelInfo>> getChannelInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertChannelInfo(ChannelInfo channelInfo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllChannels(List<ChannelInfo> channelInfoList);
}
