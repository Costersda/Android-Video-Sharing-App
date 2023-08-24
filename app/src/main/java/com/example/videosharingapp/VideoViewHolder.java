package com.example.videosharingapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videosharingapp.model.ApiModels.Items;
import com.example.videosharingapp.model.VideoInfo;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView thumbnailIv;
    TextView videoTitleTv;
    VideoInfo videoInfo;

    Context context;
    Items[] items;




    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.videoCard);
        videoTitleTv = itemView.findViewById(R.id.videoTitleTv);
        thumbnailIv = itemView.findViewById(R.id.thumbnailIv);

//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(itemView.getContext(), YouTubePlayerActivity.class);
//                intent.putExtra("VIDEO_INFO", videoInfo);
//                itemView.getContext().startActivity(intent);
//            }
//        });
    }
}
//ToDo send the URL? or add the URL to the VideoInfo class
