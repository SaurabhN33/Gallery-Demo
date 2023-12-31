package com.example.gallerydemo.Video;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallerydemo.R;

import java.util.ArrayList;


public class VideoActivity extends AppCompatActivity {
    ArrayList<Video> videoList = new ArrayList<>();
    RecyclerView recyclerView;
    VideoAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoAdapter(this, videoList);
        recyclerView.setAdapter(adapter);

        //To Get All Audio Files from device

        ContentResolver contentResolver = getContentResolver();
        Uri audioUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media.TITLE,MediaStore.Video.Media.DATA,MediaStore.Video.Media._ID};
        Cursor cursor = contentResolver.query(audioUri,projection,null,null,null);

        if (cursor != null){
            while (cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                Uri uri = ContentUris.withAppendedId(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,id);
                String thumbnilUri = uri.toString();
                Video videoModel = new Video(path,title,thumbnilUri);
                videoList.add(videoModel);
            }
        }
    }
}