package com.soul.soulproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.soul.framework.manager.MediaPlayerManager;
import com.soul.framework.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayerManager mediaPlayerManager = new MediaPlayerManager();
        AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(R.raw.source);

        mediaPlayerManager.startPlay(assetFileDescriptor);

        mediaPlayerManager.setOnProgressListener(new MediaPlayerManager.OnMusicProgressListener() {
            @Override
            public void OnProgress(int currentTime, int percent) {
                LogUtils.i("percent : " + percent);
            }
        });
    }
}
