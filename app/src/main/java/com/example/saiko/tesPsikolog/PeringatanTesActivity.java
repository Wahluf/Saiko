package com.example.saiko.tesPsikolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.saiko.R;

public class PeringatanTesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peringatan_tes);
    }

    public void masukTes(View view) {
        Intent intent = new Intent(PeringatanTesActivity.this, TestActivity.class);
        startActivity(intent);
    }
}
