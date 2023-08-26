package com.example.videosharingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videosharingapp.model.ApiModels.videos.Items;
import com.squareup.picasso.Picasso;

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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YouTubePlayerActivity.class);
                intent.putExtra("url", items[position].getId().getVideoId());
                context.startActivity(intent);

                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
