package com.example.saiko.tesPsikolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.saiko.R;

public class WelcomingTesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcoming_tes);
    }

    public void masukPeringatan(View view) {
        Intent intent = new Intent(WelcomingTesActivity.this, PeringatanTesActivity.class);
        startActivity(intent);
    }
}
