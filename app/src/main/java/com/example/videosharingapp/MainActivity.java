package com.example.videosharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    Button btnWatchYtVideo, btnListYtChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWatchYtVideo = findViewById(R.id.btnWatchYtVideo);
        btnListYtChannel = findViewById(R.id.btnListYtChannelVideo);

        btnWatchYtVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), YouTubePlayerActivity.class);
                startActivity(intent);
            }
        });

        btnListYtChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), YouTubeVideoListActivity.class);
                startActivity(intent);
            }
        });

    }

}
