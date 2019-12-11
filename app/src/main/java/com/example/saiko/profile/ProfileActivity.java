package com.example.saiko.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.saiko.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_profile);
    }

    public void masukEditProfile(View view) {
        Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void masukBerlangganan(View view) {
        Toast.makeText(getApplicationContext(),"Coming Soon!!!",Toast.LENGTH_SHORT).show();
    }

    public void menujuTentang(View view) {
        Intent intent = new Intent(ProfileActivity.this, TentangActivity.class);
        startActivity(intent);

    }

    public void masukKonselingGratis(View view) {
        Intent intent = new Intent(ProfileActivity.this, com.example.saiko.konseling.KonselingUtamaActivity.class);
        startActivity(intent);
    }
}
