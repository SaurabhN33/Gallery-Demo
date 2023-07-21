package com.example.gallerydemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class VideoActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int READ_EXTERNAL_STORAGE = 200;
    private ListView videosListView;
    private static final int PERMISSION_REQUEST_CODE = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                // Permission already granted, proceed with getting the videos
                getVideosFromStorage();
            }
        } else {
            // For devices below API level 23, permissions are granted at installation time
            getVideosFromStorage();
        }
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // we are checking the permission code.
            case PERMISSION_REQUEST_CODE:
                // in this case we are checking if the permissions are accepted or not.
                if (grantResults.length > 0) {
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        // if the permissions are accepted we are displaying a toast message
                        // and calling a method to get image path.
                        Toast.makeText(this, "Permissions Granted..", Toast.LENGTH_SHORT).show();
                        getVideosFromStorage();
                    } else {
                        // if permissions are denied we are closing the app and displaying the toast message.
                        Toast.makeText(this, "Permissions denied, Permissions are required to use the app..", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    // Function to retrieve videos from device storage using MediaStore API
    private void getVideosFromStorage() {
        List<String> videoPaths = VideoUtil.getAllVideosFromDevice(this);

        if (videoPaths.isEmpty()) {
            Toast.makeText(this, "No videos found on the device.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display the list of video paths in a ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, videoPaths);
        videosListView.setAdapter(adapter);
    }
}