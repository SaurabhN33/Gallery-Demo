package com.example.gallerydemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gallerydemo.Audio.AudioActivity;
import com.example.gallerydemo.Images.MainActivity;
import com.example.gallerydemo.Video.VideoActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnImg,btnVid,btnAudio;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnImg = findViewById(R.id.btnImage);
        btnVid = findViewById(R.id.btnVideo);
        btnAudio = findViewById(R.id.btnAudio);

        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
        btnVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, VideoActivity.class);
                startActivity(i);

            }
        });
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, AudioActivity.class);
                startActivity(i);

            }
        });

    }
}