package com.example.onlineclasses;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiServiceClass {
//    @GET("/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4")
//    @Streaming
//    public Call<ResponseBody> getData();

    @GET()
    @Streaming
    public Call<ResponseBody> getData(@Url String url);
}
