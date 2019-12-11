package com.example.saiko.masuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.saiko.MainActivity;
import com.example.saiko.R;

public class MasukActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_masuk);
    }

    //Intent ke tampilan Daftar
    public void launchDaftar(View view) {
        Intent intent = new Intent(MasukActivity.this, DaftarActivity.class);
        startActivity(intent);
    }

    public void launchMainMenu(View view) {
        Intent intent = new Intent(MasukActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
