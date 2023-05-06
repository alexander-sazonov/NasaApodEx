package com.example.nasaapodex;

import android.app.Application;

import okhttp3.OkHttpClient;

public class App extends Application {
    private static OkHttpClient client;

    public static OkHttpClient getClient(){
        if (client == null){
            client = new OkHttpClient();
        }
        return client;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
