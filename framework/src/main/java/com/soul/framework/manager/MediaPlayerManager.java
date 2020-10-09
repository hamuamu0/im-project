package com.soul.framework.manager;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.soul.framework.utils.LogUtils;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-09
 * @Describe: 媒体播放
 */
public class MediaPlayerManager {

    private MediaPlayer mMediaPlayer;

    //设置播放状态
    public static final  int MEDIA_STATUS_PLAY = 0;
    //设置暂停状态
    public static final  int MEDIA_STATUS_PAUSE = 1;
    //设置停止状态
    public static final  int MEDIA_STATUS_STOP = 2;
    //当前状态
    public static int MEDIA_STATUS = MEDIA_STATUS_STOP;

    private static final int H_PROGRESS = 1000;

    OnMusicProgressListener onMusicProgressListener;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what){
                case H_PROGRESS :

                    if (onMusicProgressListener != null){
                        //拿到当前时长
                        int currentPosition = getCurrentPosition();
                        int percent = (int)((((float) currentPosition) / ((float)mMediaPlayer.getDuration()) * 100));

                        onMusicProgressListener.OnProgress(currentPosition,percent);
                        if (percent < 100){
                            handler.sendEmptyMessageDelayed(H_PROGRESS,1000);
                        }



                    }

                    break;
            }
            return false;
        }
    });

    public MediaPlayerManager(){
        mMediaPlayer = new MediaPlayer();
    }

    /**
     * 是否正在播放
     * @return
     */
    public boolean isPlaying(){
        return mMediaPlayer.isPlaying();
    }

    /**
     * 开始播放
     * @param path
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startPlay(AssetFileDescriptor path){
        try {
            handler.sendEmptyMessage(H_PROGRESS);
            //万一播放第二个或者第三个歌曲的话，这些状态都需要重置
            mMediaPlayer.reset();
            //设置播放资源
            mMediaPlayer.setDataSource(path);
            //装载
            mMediaPlayer.prepare();
            //开始播放
            mMediaPlayer.start();
            MEDIA_STATUS = MEDIA_STATUS_PLAY;
        } catch (IOException e) {
            LogUtils.e(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 暂停播放
     */
    public void pausePlay(){
        if (isPlaying()){
            mMediaPlayer.pause();
            MEDIA_STATUS = MEDIA_STATUS_PAUSE;
            handler.removeMessages(H_PROGRESS);
        }
    }

    /**
     * 继续播放
     */
    public void continuePlay(){
        mMediaPlayer.start();
        MEDIA_STATUS = MEDIA_STATUS_PLAY;
        handler.sendEmptyMessage(H_PROGRESS);
    }

    /**
     * 停止播放
     */
    public void stopPlay(){
        mMediaPlayer.stop();
        MEDIA_STATUS = MEDIA_STATUS_STOP;
        handler.removeMessages(H_PROGRESS);
    }

    /**
     * 获取当前位置
     * @return
     */
    public int getCurrentPosition(){
        return mMediaPlayer.getCurrentPosition();
    }

    /**
     * 获取总时长
     * @return
     */
    public int getDuration(){
        return mMediaPlayer.getDuration();
    }

    /**
     * 播放结束
     * @param listener
     */
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener){
        mMediaPlayer.setOnCompletionListener(listener);
    }

    /**
     * 播放错误
     * @param listener
     */
    public void setOnErrorListener(MediaPlayer.OnErrorListener listener){
        mMediaPlayer.setOnErrorListener(listener);
    }

    /**
     * 设置是否循环
     * @param isLoop
     */
    public void setLooping(boolean isLoop){
        mMediaPlayer.setLooping(isLoop);
    }

    /**
     * 设置位置
     * @param ms
     */
    public void seekTo(int ms){
        mMediaPlayer.seekTo(ms);
    }


    public void setOnProgressListener(OnMusicProgressListener listener){
        onMusicProgressListener = listener;
    }

    public interface OnMusicProgressListener{
        void OnProgress(int currentTime, int percent);
    }

}
