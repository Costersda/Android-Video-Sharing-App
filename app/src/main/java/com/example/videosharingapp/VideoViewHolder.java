package com.example.videosharingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class VideoViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView thumbnailIv;
    TextView videoTitleTv;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.videoCard);
        videoTitleTv = itemView.findViewById(R.id.videoTitleTv);
        thumbnailIv = itemView.findViewById(R.id.thumbnailIv);


    }
}

