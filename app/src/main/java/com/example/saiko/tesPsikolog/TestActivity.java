package com.example.saiko.tesPsikolog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saiko.R;

public class TestActivity extends AppCompatActivity {

    private RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8, rg9, rg10, rg11, rg12, rg13, rg14;
    TextView hasil;
    Button btnSubmit;
    String n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14;
    int jumlah;
    String kesimpulan;


    private String KEY_RESULT = "HASIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_test);

        rg1 = findViewById(R.id.rg_1);
        rg2 = findViewById(R.id.rg_2);
        rg3 = findViewById(R.id.rg_3);
        rg4 = findViewById(R.id.rg_4);
        rg5 = findViewById(R.id.rg_5);
        rg6 = findViewById(R.id.rg_6);
        rg7 = findViewById(R.id.rg_7);
        rg8 = findViewById(R.id.rg_8);
        rg9 = findViewById(R.id.rg_9);
        rg10 = findViewById(R.id.rg_10);
        rg11 = findViewById(R.id.rg_11);
        rg12 = findViewById(R.id.rg_12);
        rg13 = findViewById(R.id.rg_13);
        rg14 = findViewById(R.id.rg_14);
        hasil = findViewById(R.id.tv_hasil);

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int getN1 = rg1.getCheckedRadioButtonId();
                    if (getN1 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN1);
                        n1 = selectedRadioButton.getText().toString();
                    }

                    int getN2 = rg2.getCheckedRadioButtonId();
                    if (getN2 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN2);
                        n2 = selectedRadioButton.getText().toString();
                    }

                    int getN3 = rg3.getCheckedRadioButtonId();
                    if (getN3 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN3);
                        n3 = selectedRadioButton.getText().toString();
                    }

                    int getN4 = rg4.getCheckedRadioButtonId();
                    if (getN4 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN4);
                        n4 = selectedRadioButton.getText().toString();
                    }

                    int getN5 = rg5.getCheckedRadioButtonId();
                    if (getN5 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN5);
                        n5 = selectedRadioButton.getText().toString();
                    }

                    int getN6 = rg6.getCheckedRadioButtonId();
                    if (getN6 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN6);
                        n6 = selectedRadioButton.getText().toString();
                    }

                    int getN7 = rg7.getCheckedRadioButtonId();
                    if (getN7 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN7);
                        n7 = selectedRadioButton.getText().toString();
                    }

                    int getN8 = rg8.getCheckedRadioButtonId();
                    if (getN8 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN8);
                        n8 = selectedRadioButton.getText().toString();
                    }

                    int getN9 = rg9.getCheckedRadioButtonId();
                    if (getN9 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN9);
                        n9 = selectedRadioButton.getText().toString();
                    }

                    int getN10 = rg10.getCheckedRadioButtonId();
                    if (getN10 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN10);
                        n10 = selectedRadioButton.getText().toString();
                    }

                    int getN11 = rg11.getCheckedRadioButtonId();
                    if (getN11 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN11);
                        n11 = selectedRadioButton.getText().toString();
                    }

                    int getN12 = rg12.getCheckedRadioButtonId();
                    if (getN12 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN12);
                        n12 = selectedRadioButton.getText().toString();
                    }

                    int getN13 = rg13.getCheckedRadioButtonId();
                    if (getN13 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN13);
                        n13 = selectedRadioButton.getText().toString();
                    }

                    int getN14 = rg14.getCheckedRadioButtonId();
                    if (getN14 != -1) {
                        RadioButton selectedRadioButton = findViewById(getN14);
                        n14 = selectedRadioButton.getText().toString();
                    }

                    if (rg1 != null && rg2 != null && rg3 != null && rg4 != null && rg5 != null && rg6
                            != null && rg7 != null && rg8 != null && rg9 != null && rg10 != null && rg11
                            != null && rg12 != null && rg13 != null && rg14 != null) {

                        //Ubah String ke Integer
                        int r1 = Integer.parseInt(n1);
                        int r2 = Integer.parseInt(n2);
                        int r3 = Integer.parseInt(n3);
                        int r4 = Integer.parseInt(n4);
                        int r5 = Integer.parseInt(n5);
                        int r6 = Integer.parseInt(n6);
                        int r7 = Integer.parseInt(n7);
                        int r8 = Integer.parseInt(n8);
                        int r9 = Integer.parseInt(n9);
                        int r10 = Integer.parseInt(n10);
                        int r11 = Integer.parseInt(n11);
                        int r12 = Integer.parseInt(n12);
                        int r13 = Integer.parseInt(n13);
                        int r14 = Integer.parseInt(n14);

                        jumlah = r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9 + r10 + r11 + r12 + r13 + r14;

                        if (jumlah >= 59) {
                            kesimpulan = "Kamu Sedang Mengalami Kondisi yang Sehat, Selalu Berpikir Positif dan tetap semangat";
                        } else {
                            kesimpulan = "Kamu Kurang Sehat, Silahkan berkonsultasi kepada seseorang yang ahli dalam bidang ini. " +
                                    "Kamu juga perlu melakukan meditasi agar memberikanmu ketenangan jiwa";
                        }
                        //hasil.setText(kesimpulan);
                        Intent intent = new Intent(TestActivity.this, HasilTestActivity.class);
                        intent.putExtra(KEY_RESULT, kesimpulan);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplication(), "Pastikan semua pilihan telah terisi", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplication(), "Pastikan semua pilihan telah terisi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
