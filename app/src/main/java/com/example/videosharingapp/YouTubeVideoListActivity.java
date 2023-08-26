package com.example.videosharingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.videosharingapp.model.API.SingleChannelRetrofit;
import com.example.videosharingapp.model.API.SingleVideoRetrofit;
import com.example.videosharingapp.model.ApiModels.channels.ChannelItems;
import com.example.videosharingapp.model.ApiModels.channels.ChannelModel;
import com.example.videosharingapp.model.ApiModels.videos.Items;
import com.example.videosharingapp.model.ApiModels.videos.VideoModel;
import com.example.videosharingapp.model.ChannelDoa;
import com.example.videosharingapp.model.ChannelInfo;
import com.example.videosharingapp.model.VideoDoa;
import com.example.videosharingapp.model.VideoInfo;
import com.example.videosharingapp.model.YouTubeDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YouTubeVideoListActivity extends AppCompatActivity {

    RecyclerView channelsRecyclerView;
    ProgressBar progressBar;

    YouTubeDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_video_list);

        channelsRecyclerView = findViewById(R.id.channelRecycleView);
        progressBar = findViewById(R.id.progress_circular);

        channelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        channelsRecyclerView.hasFixedSize();

        database = Room.databaseBuilder(getApplicationContext(),
                YouTubeDatabase.class, "YTDatabase").build();

        makeApiCall();
        makeChannelApiCall();
    }

    private void makeApiCall() {
        Call<VideoModel> videoModelCall = SingleVideoRetrofit.getmInstance().getAPI().getVideosDetails(
                BuildConfig.YOUTUBE_API_KEY,
                "UCEWpbFLzoYGPfuWUMFPSaoA",
                "snippet",
                "date",
                "50",
                "video"
        );
        videoModelCall.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Items[] videoItems = response.body().getItems();
                    List<VideoInfo> videoInfoList = new ArrayList<>();

                    // Turn the response into VideoInfo Objects
                    for (Items videoItem : videoItems) {
                        String videoUrl = "https://www.youtube.com/watch?v=" + videoItem.getId().getVideoId();
                        String videoTitle = videoItem.getSnippet().getTitle();
                        String videoThumbnailUrl = videoItem.getSnippet().getThumbnails().getHigh().getUrl();

                        VideoInfo videoInfo = new VideoInfo(videoUrl, videoTitle, videoThumbnailUrl);
                        videoInfoList.add(videoInfo);
                    }

                    // Save videoInfoList to Room database
                    if (!videoInfoList.isEmpty()) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                VideoDoa dao = database.getVideoDatabaseDao();
                                for (VideoInfo videoInfo : videoInfoList) {
                                    dao.insertVideoInfo(videoInfo);

                                    // Check if the document exists in Firestore before adding
                                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                                    firestore.collection("videos")
                                            .whereEqualTo("videoUrl", videoInfo.getVideoUrl())
                                            .get()
                                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                @Override
                                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                    if (queryDocumentSnapshots.isEmpty()) {
                                                        // Document doesn't exist, add it to Firestore
                                                        firestore.collection("videos").add(videoInfo);
                                                    }
                                                }
                                            });
                                }
                            }
                        }).start();
                    }

                    // Set the RecyclerView
                    setRecyclerView(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {

            }
        });
    }

    private void setRecyclerView(Items[] items) {
        VideosAdapter videosAdapter = new VideosAdapter(this, items);
        channelsRecyclerView.setAdapter(videosAdapter);
        channelsRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void makeChannelApiCall() {
        Call<ChannelModel> channelModelCall = SingleChannelRetrofit.getmInstance().getAPI().getChannelDetails(
                BuildConfig.YOUTUBE_API_KEY,
                "snippet,statistics",
                "UCEWpbFLzoYGPfuWUMFPSaoA",
                "viewCount,subscriberCount,videoCount"
        );
        channelModelCall.enqueue(new Callback<ChannelModel>() {
            @Override
            public void onResponse(Call<ChannelModel> call, Response<ChannelModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ChannelItems[] channelItems = response.body().getItems();
                    List<ChannelInfo> channelInfoList = new ArrayList<>();

                    for (ChannelItems channelItem : channelItems) {
                        String channelId = channelItem.getId();
                        String channelTitle = channelItem.getSnippet().getTitle();
                        String channelDescription = channelItem.getSnippet().getDescription();
                        String channelVideos = channelItem.getStatistics().getVideoCount();
                        String channelViews = channelItem.getStatistics().getViewCount();
                        String channelSubscribers = channelItem.getStatistics().getSubscriberCount();

                        ChannelInfo channelInfo = new ChannelInfo(channelId, channelTitle,
                                channelDescription, channelVideos, channelViews, channelSubscribers);
                        //channelInfoList.add(channelInfo);

                        //if (!channelInfoList.isEmpty()) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ChannelDoa doa = database.getChannelDatabaseDao();
                                //for (ChannelInfo channelInfo : channelInfoList) {
                                doa.insertChannelInfo(channelInfo);

                                // Check if the document exists in Firestore before adding
                                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                                firestore.collection("channels")
                                        .whereEqualTo("channelId", channelInfo.getChannelId())
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                if (queryDocumentSnapshots.isEmpty()) {
                                                    // Document doesn't exist, add it to Firestore
                                                    firestore.collection("channels").add(channelInfo);
                                                }
                                            }
                                        });
                                // }
                            }
                        }).start();
                        //}


                    }
                }


            }

            @Override
            public void onFailure(Call<ChannelModel> call, Throwable t) {
                Log.e("cApi call", "call failed");
            }
        });
    }

}

