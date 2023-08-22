package com.example.videosharingapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface YouTubeDatabaseAccessObject {
    @Query("SELECT * FROM Channels")
    LiveData<List<ChannelInfo>> getChannelsInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertChannelInfo(ChannelInfo channelInfo);

}
