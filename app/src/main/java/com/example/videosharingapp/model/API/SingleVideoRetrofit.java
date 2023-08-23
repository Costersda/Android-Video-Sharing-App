package com.example.videosharingapp.model.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleVideoRetrofit {

    private static SingleVideoRetrofit mInstance;
    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    private static Retrofit retrofit;

    private SingleVideoRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized SingleVideoRetrofit getmInstance() {
        if (mInstance == null) {
            mInstance = new SingleVideoRetrofit();
        }
        return mInstance;
    }

    public API getAPI(){
        return retrofit.create(API.class);
    }

}
