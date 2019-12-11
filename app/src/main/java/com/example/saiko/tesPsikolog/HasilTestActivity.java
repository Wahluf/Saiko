package com.example.saiko.tesPsikolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.saiko.MainActivity;
import com.example.saiko.R;

public class HasilTestActivity extends AppCompatActivity {

    private String hasil;
    private String KEY_RESULT = "HASIL";
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_test);

        tvHasil = findViewById(R.id.tv_hasil);

        Bundle extras = getIntent().getExtras();
        hasil = extras.getString(KEY_RESULT);
        tvHasil.setText(hasil);
    }

    public void masukHome(View view) {
        Intent intent = new Intent(HasilTestActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
