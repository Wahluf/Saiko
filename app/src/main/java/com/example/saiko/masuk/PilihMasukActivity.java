package com.example.saiko.masuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.saiko.FirstActivity;
import com.example.saiko.MainActivity;
import com.example.saiko.R;

public class PilihMasukActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_pilih_masuk);
    }

    //Intent ke tampilan Masuk
    public void launchMasuk(View view) {
        Intent intent = new Intent(PilihMasukActivity.this, MasukActivity.class);
        startActivity(intent);
    }

    //Intent ke tampilan Daftar
    public void launchDaftar(View view) {
        Intent intent = new Intent(PilihMasukActivity.this, DaftarActivity.class);
        startActivity(intent);
    }
}
