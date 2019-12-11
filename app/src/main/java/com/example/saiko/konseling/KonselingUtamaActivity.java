package com.example.saiko.konseling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.saiko.konseling.KonselingData;
import com.example.saiko.konseling.KonselingDetailActivity;
import com.example.saiko.konseling.listKonselingData;
import com.example.saiko.konseling.Konseling;
import com.example.saiko.R;

import java.util.ArrayList;

public class KonselingUtamaActivity extends AppCompatActivity {

    //inisialisasi RecycleView yang telah dibuat
    private RecyclerView rvKonseling;
    //memanggil data dari kelas ProvinsiData
    private ArrayList<Konseling> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konseling_utama);



        rvKonseling = findViewById(R.id.rv_konseling);
        rvKonseling.setHasFixedSize(true);
        list.addAll(KonselingData.getListData());
        showRecyclerList();
    }

    //Untuk menampilkan data pada RecyclerView
    private void showRecyclerList() {
        rvKonseling.setLayoutManager(new LinearLayoutManager(this));
        listKonselingData listKonselingData = new listKonselingData(list);
        rvKonseling.setAdapter(listKonselingData);
        listKonselingData.setOnItemClickCallback(new listKonselingData.OnItemClickCallback() {
            @Override
            public void onItemClicked(Konseling data) {
                moveToDetailActivity(data);
            }
        });
    }

    //Aksi untuk melihat detail informasi
    private void moveToDetailActivity(Konseling data) {
        Intent intent = new Intent(KonselingUtamaActivity.this, KonselingDetailActivity.class);
        intent.putExtra(KonselingDetailActivity.EXTRA_NAMA,data.getName());
        intent.putExtra(KonselingDetailActivity.EXTRA_KATEGORI, data.getKategori());
        intent.putExtra(KonselingDetailActivity.EXTRA_LOKASI, data.getLokasi());
        intent.putExtra(KonselingDetailActivity.EXTRA_DESKRIPSI, data.getDetail());
        intent.putExtra(KonselingDetailActivity.EXTRA_WAKTU, data.getWaktuLayanan());
        intent.putExtra(KonselingDetailActivity.EXTRA_KONTAK, data.getNomorLayanan());
        intent.putExtra(KonselingDetailActivity.EXTRA_KONSELOR_PROFESIONAL, data.getDaftarKonselor());
        intent.putExtra(KonselingDetailActivity.EXTRA_FOTO, data.getFoto());
        //intent.putExtra(KonselingDetailActivity.EXTRA_WEBSITE, data.getWebsite());
        startActivity(intent);
    }


}
