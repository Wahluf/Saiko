package com.example.saiko.masuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.saiko.MainActivity;
import com.example.saiko.R;
import com.example.saiko.profile.EditProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PilihMasukActivity extends AppCompatActivity {
    //Variabel firebase
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_pilih_masuk);

        firebaseAuth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Intent intent = new Intent(PilihMasukActivity.this, MainActivity.class);
                    startActivity(intent);
//                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);
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
