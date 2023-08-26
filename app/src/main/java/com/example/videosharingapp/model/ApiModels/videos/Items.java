package com.example.videosharingapp.model.ApiModels.videos;

import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("kind")
    String kind;

    @SerializedName("etag")
    String etag;

    @SerializedName("id")
    Id id;

    @SerializedName("snippet")
    Snippet snippet;


    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getKind() {
        return kind;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
    public String getEtag() {
        return etag;
    }

    public void setId(Id id) {
        this.id = id;
    }
    public Id getId() {
        return id;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
    public Snippet getSnippet() {
        return snippet;
    }

}