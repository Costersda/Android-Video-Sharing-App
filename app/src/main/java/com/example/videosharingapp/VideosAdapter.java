package com.example.videosharingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videosharingapp.model.VideoInfo;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    List<VideoInfo> videos;

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recyclerview_video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.videoInfo = videos.get(position);
        holder.videoTitleTv.setText(videos.get(position).getVideoTitle());
        holder.videoViewCountTv.setText(videos.get(position).getVideoViewCount());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void updateData(List<VideoInfo> videos){
        this.videos = videos;
        notifyDataSetChanged();
    }
}
