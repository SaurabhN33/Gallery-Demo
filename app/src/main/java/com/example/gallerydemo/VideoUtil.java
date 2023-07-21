package com.example.gallerydemo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class VideoUtil {

    public static List<String> getAllVideosFromDevice(Context context) {
        List<String> videoPaths = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            while (cursor.moveToNext()) {
                String videoPath = cursor.getString(columnIndex);
                videoPaths.add(videoPath);
            }
            cursor.close();
        } else {
            Log.e("VideoUtil", "Cursor is null.");
        }

        return videoPaths;
    }
}
