package com.example.saiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.saiko.intro.IntroActivity;
import com.example.saiko.masuk.PilihMasukActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_first);
    }

    //Intent Menuju Halaman Berikutnya
    public void launchPilihMasukActivity(View view) {
        Intent intent = new Intent(FirstActivity.this, IntroActivity.class);
        startActivity(intent);
    }
}


