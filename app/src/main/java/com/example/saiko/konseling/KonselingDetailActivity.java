package com.example.saiko.konseling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.saiko.R;


public class KonselingDetailActivity extends AppCompatActivity {

    //mendeklarasikan attribut yang akan dipanggil
    public static String EXTRA_FOTO = "foto_tempat";
    public static String EXTRA_NAMA = "nama_tempat";
    public static String EXTRA_KATEGORI = "kategori_tempat";
    public static String EXTRA_LOKASI = "lokasi_tempat";
    public static String EXTRA_DESKRIPSI = "deskripsi_tempat";
    public static String EXTRA_WAKTU = "waktu_layanan";
    public static String EXTRA_KONTAK = "kontak_tempat";
    public static String EXTRA_KONSELOR_PROFESIONAL = "konselor_profesional";
    //public static String EXTRA_WEBSITE = "website";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konseling_detail);

        //Menginisialisasi attribut yang akan digunakan dalam menampilkan detail aktivity
        ImageView tempatFoto = findViewById(R.id.img_item_profil);
        TextView tempatName = findViewById(R.id.tv_konseling);
        TextView kategori = findViewById(R.id.tv_kategori);
        TextView lokasi = findViewById(R.id.tv_alamat);
        TextView deskripsi = findViewById(R.id.tv_deskripsi);
        TextView waktu = findViewById(R.id.tv_waktu_layananan);
        TextView kontak = findViewById(R.id.tv_nomor);
        TextView daftarKonselorProfesional = findViewById(R.id.tv_daftar_konselor);
        //TextView website = findViewById(R.id.tv_website);

        Glide.with(this).load(getIntent().getIntExtra(EXTRA_FOTO, 0)).into(tempatFoto);
        tempatName.setText(getIntent().getStringExtra(EXTRA_NAMA));
        kategori.setText(getIntent().getStringExtra(EXTRA_KATEGORI));
        lokasi.setText(getIntent().getStringExtra(EXTRA_LOKASI));
        deskripsi.setText(getIntent().getStringExtra(EXTRA_DESKRIPSI));
        waktu.setText(getIntent().getStringExtra(EXTRA_WAKTU));
        kontak.setText(getIntent().getStringExtra(EXTRA_KONTAK));
        daftarKonselorProfesional.setText(getIntent().getStringExtra(EXTRA_KONSELOR_PROFESIONAL));
        //website.setText(getIntent().getStringExtra(EXTRA_WEBSITE));


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_NAMA));
        }

    }
}
