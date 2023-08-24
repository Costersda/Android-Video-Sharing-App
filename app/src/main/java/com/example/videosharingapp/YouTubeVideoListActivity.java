package com.example.videosharingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.videosharingapp.model.API.SingleVideoRetrofit;
import com.example.videosharingapp.model.ApiModels.Items;
import com.example.videosharingapp.model.ApiModels.VideoModel;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YouTubeVideoListActivity extends AppCompatActivity {

    RecyclerView channelsRecyclerView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_video_list);

        channelsRecyclerView = findViewById(R.id.channelRecycleView);
        progressBar = findViewById(R.id.progress_circular);

        channelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        channelsRecyclerView.hasFixedSize();

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
                setRecyclerView(response.body().getItems());
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

}

