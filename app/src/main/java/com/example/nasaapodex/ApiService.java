package com.example.nasaapodex;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiService {

    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod";
    private static final String API_KEY = "wgqap22aPTYiF7UzBddMwaolVUcB5StMOwzPTfZD";
    private OkHttpClient client;

    public ApiService(OkHttpClient client) {
        this.client = client;
    }
    public void getTodayData(ApiServiceListener listener){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("api_key",API_KEY);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if(response.isSuccessful()){
                        if(response.body() != null){
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            ApodResponse apodResponse = gson.fromJson(response.body().charStream(),ApodResponse.class);
                            listener.onDataUpdate(apodResponse);
                        }
                    }
            }
        });
    }
    interface ApiServiceListener{
        void onDataUpdate(ApodResponse apodResponse);
    }
}
