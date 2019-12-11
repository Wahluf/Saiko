package com.example.saiko.meditasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.saiko.R;

public class MenuMeditasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_meditasi);
    }

    public void masukSesi(View view) {
        Intent intent = new Intent(MenuMeditasiActivity.this, SesiMeditasiActivity.class);
        startActivity(intent);
    }

    public void masukTunggu(View view) {
        Toast.makeText(getApplicationContext(),"Mohon Maaf untuk sesi ini, Anda harus berlangganan",Toast.LENGTH_SHORT).show();
    }
}
