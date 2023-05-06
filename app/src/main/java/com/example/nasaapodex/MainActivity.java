package com.example.nasaapodex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ImageView imageView;
    private TextView titleTv;

    private final String API_KEY =  "wgqap22aPTYiF7UzBddMwaolVUcB5StMOwzPTfZD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.get_btn);
        imageView = findViewById(R.id.image);
        titleTv = findViewById(R.id.title);
        btn.setOnClickListener(view -> {
            get_data();
        });

    }
    private void get_data(){
        ApiService apiService = App.getApi();
        apiService.getData(API_KEY).enqueue(new Callback<ApodResponse>() {
            @Override
            public void onResponse(Call<ApodResponse> call, Response<ApodResponse> response) {
                ApodResponse apodResponse = response.body();
                titleTv.setText(apodResponse.title);
                Picasso.get().load(apodResponse.url).into(imageView);
            }

            @Override
            public void onFailure(Call<ApodResponse> call, Throwable t) {

            }
        });
    }
}