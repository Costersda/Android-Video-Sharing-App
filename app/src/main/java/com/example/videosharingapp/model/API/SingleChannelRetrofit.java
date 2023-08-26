package com.example.videosharingapp.model.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleChannelRetrofit {

    private static SingleChannelRetrofit mInstance;
    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    private static Retrofit retrofit;

    private SingleChannelRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized SingleChannelRetrofit getmInstance() {
        if (mInstance == null) {
            mInstance = new SingleChannelRetrofit();
        }
        return mInstance;
    }

    public API getAPI(){
        return retrofit.create(API.class);
    }

}
