package com.example.videosharingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videosharingapp.model.ApiModels.Items;
import com.example.videosharingapp.model.VideoInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    Context context;
    Items[] items;

    public VideosAdapter(Context context, Items[] items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.videoTitleTv.setText(items[position].getSnippet().getTitle());
        Picasso.get().load(items[position].getSnippet().getThumbnails().getHigh().getUrl()).into(holder.thumbnailIv);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
