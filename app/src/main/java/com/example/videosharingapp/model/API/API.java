package com.example.videosharingapp.model.API;

import com.example.videosharingapp.model.ApiModels.channels.ChannelModel;
import com.example.videosharingapp.model.ApiModels.videos.VideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("search")
    Call<VideoModel> getVideosDetails(@Query("key") String key,
                                      @Query("channelId") String channelId,
                                      @Query("part") String part,
                                      @Query("order") String order,
                                      @Query("maxResults") String maxResults,
                                      @Query("type") String type
                                            );

    @GET("channels")
    Call<ChannelModel> getChannelDetails(@Query("key") String key,
                                         @Query("part") String part,
                                         @Query("id") String channelId,
                                         @Query("statistics") String statistics);

}


