package com.example.gallerydemo.Video;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gallerydemo.R;

public class VideoPlayerActivity extends AppCompatActivity {
    VideoView videoView;

    MediaController mediaController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        String video_path = getIntent().getStringExtra("videoPath");

        videoView = findViewById(R.id.video_view);

        mediaController = new MediaController(VideoPlayerActivity.this);

        videoView.setVideoPath(video_path);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        videoView.start();
    }
}