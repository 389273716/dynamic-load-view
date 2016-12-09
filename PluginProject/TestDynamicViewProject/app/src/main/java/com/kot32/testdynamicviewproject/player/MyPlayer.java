package com.kot32.testdynamicviewproject.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.kot32.testdynamicviewproject.R;

import java.io.IOException;

/**
 * Created by kot32 on 16/12/8.
 */
public class MyPlayer extends View {

    public MediaPlayer mediaPlayer;

    public MyPlayer(Context context) {
        super(context);
        init();
    }

    public MyPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.pig);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        playUrl("http://cdn.ifancc.com/TomaToDo/bgms/my_hbx.mp3");
        Toast.makeText(getContext(), "开始播放", Toast.LENGTH_SHORT).show();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Toast.makeText(getContext(), "继续播放", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer.pause();
                    Toast.makeText(getContext(), "暂停播放", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void playUrl(String videoUrl) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.prepare();//prepare之后自动播放
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            mediaPlayer.stop();
            mediaPlayer.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
