package com.example.videosharingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class YouTubeVideoListActivity extends AppCompatActivity {

    RecyclerView channelsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_video_list);

        channelsRecyclerView = findViewById(R.id.channelRecycleView);
    }
}