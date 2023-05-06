package com.example.nasaapodex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ImageView imageView;
    private TextView titleTv;

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
        ApiService service = new ApiService(App.getClient());
        service.getTodayData(new ApiService.ApiServiceListener() {
            @Override
            public void onDataUpdate(ApodResponse apodResponse) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        titleTv.setText(apodResponse.title);
                        Picasso.get().load(apodResponse.url).into(imageView);
                    }
                });
            }
        });
    }
}