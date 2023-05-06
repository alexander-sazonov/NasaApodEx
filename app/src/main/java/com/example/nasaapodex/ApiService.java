package com.example.nasaapodex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/planetary/apod")
    Call<ApodResponse> getData(@Query("api_key") String key);
}
