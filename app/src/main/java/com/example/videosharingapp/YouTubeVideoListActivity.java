package com.example.videosharingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.videosharingapp.model.API.SingleVideoRetrofit;
import com.example.videosharingapp.model.ApiModels.Items;
import com.example.videosharingapp.model.ApiModels.VideoModel;
import com.example.videosharingapp.model.VideoInfo;
import com.example.videosharingapp.model.YouTubeDatabase;
import com.example.videosharingapp.model.YouTubeDatabaseAccessObject;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


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
    YouTubeDatabaseAccessObject youTubeDoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_video_list);

        channelsRecyclerView = findViewById(R.id.channelRecycleView);
        progressBar = findViewById(R.id.progress_circular);

        channelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        channelsRecyclerView.hasFixedSize();

        database = Room.databaseBuilder(getApplicationContext(),
                YouTubeDatabase.class, "Video-database").build();

        makeApiCall();
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
                    // Set the RecyclerView
                    setRecyclerView(response.body().getItems());

                    Items[] videoItems = response.body().getItems();
                    List<VideoInfo> videoInfoList = new ArrayList<>();
                    // Turn the response into VideoInfo Objects
                    for (Items videoItem : videoItems) {
                        String videoUrl = "https://www.youtube.com/watch?v=" + videoItem.getId().getVideoId();
                        String videoTitle = videoItem.getSnippet().getTitle();
                        String videoThumbnailUrl = videoItem.getSnippet().getThumbnails().getHigh().getUrl();

                        VideoInfo videoInfo = new VideoInfo(videoUrl, videoTitle, videoThumbnailUrl);
                        videoInfoList.add(videoInfo);

                        Log.e("video", String.valueOf(videoInfoList.size()));
                    }

                    // Save videoInfoList to Room database
                    if (!videoInfoList.isEmpty()) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                database.getDatabaseDao().insertAll(videoInfoList);
                            }
                        }).start();
                    }

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

//    private void writeToFirebase(VideoInfo videoInfo){
//        // Connecting with the Firebase Realtime Database
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference channelCollection = db.collection("yt_channel_data");
//        DocumentReference channelRef = channelCollection.document(videoInfo.getVideoUrl());
//        channelRef.set(videoInfo)
//                .addOnSuccessListener(
//                        new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                youTubeDoa.insertVideoInfo(videoInfo);
//                            }
//                        }
//                )
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("FireStoreError", "cannot add data in FireStore");
//                    }
//                });
//    }

}

