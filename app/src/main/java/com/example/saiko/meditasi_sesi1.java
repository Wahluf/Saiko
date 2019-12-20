package com.example.saiko;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.saiko.meditasi.SesiMeditasiActivity;

import java.sql.Struct;

public class meditasi_sesi1 extends AppCompatActivity {

    Button playbtn, next_btn, prev_btn;
    SeekBar volume_bar, positionbar;
    TextView waktusekarang, waktutersisa;
    MediaPlayer mp;
    int total_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide();
        setContentView(R.layout.activity_meditasi_sesi1);

        playbtn = (Button) findViewById(R.id.play_button);
        waktusekarang = (TextView) findViewById(R.id.waktu_sekarang);
        waktutersisa = (TextView) findViewById(R.id.waktu_tersisa);

        mp = MediaPlayer.create(this, R.raw.hujan_compress);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        total_time = mp.getDuration();

        positionbar = (SeekBar) findViewById(R.id.position_bar);
        positionbar.setMax(total_time);
        positionbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser){
                            mp.seekTo(progress);
                            positionbar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null){
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }

                }
            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int currentPosition = msg.what;
            positionbar.setProgress(currentPosition);

            String elapsedTime = createTimeLabel(currentPosition);
            waktusekarang.setText(elapsedTime);

            String remainingTime = createTimeLabel(total_time - currentPosition);
            waktutersisa.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time){
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";

        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnClick(View view){
        if (!mp.isPlaying()){
            mp.start();
            playbtn.setBackgroundResource(R.drawable.pause);
        }
        else{
            mp.pause();
            playbtn.setBackgroundResource(R.drawable.play);
        }
    }

    public void nextBtnClick(View view) {
        mp.stop();
        Intent intent = new Intent(meditasi_sesi1.this, meditasi_sesi2.class);
        startActivity(intent);
    }

    public void backBtn(View view) {
        mp.stop();
        Intent intent = new Intent(meditasi_sesi1.this, SesiMeditasiActivity.class);
        startActivity(intent);
    }

    public void prevBtnClick5(View view) {
        mp.stop();
        Intent intent = new Intent(meditasi_sesi1.this, meditasi_sesi5.class);
        startActivity(intent);
    }
}
