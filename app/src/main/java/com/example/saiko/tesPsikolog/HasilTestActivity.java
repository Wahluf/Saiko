package com.example.saiko.tesPsikolog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.saiko.MainActivity;
import com.example.saiko.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HasilTestActivity extends AppCompatActivity {
    //Variabel Firebase
    private DatabaseReference db, dbu, dbR1;
    private FirebaseDatabase dbf, db1;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private int aNilaiTes;
    private String hasil;
    private String KEY_RESULT = "HASIL";
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_test);

        tvHasil = findViewById(R.id.tv_hasil);

        // Baca data
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        dbf = FirebaseDatabase.getInstance();
        db = dbf.getReference("Data Pengguna");

        Query query = db.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String rNilaiTes = "" + ds.child("nilaiTes").getValue();

                    aNilaiTes = Integer.parseInt(rNilaiTes);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (aNilaiTes >= 59) {
            hasil = "Selamat!!! \n\nHasil test yang  telah kamu lakukan menunjukkan Kamu Dalam Kondisi yang Sehat, Anda dapat melakukan layanan meditasi jika anda ingin mencari ketenangan..\n";
        } else {
            hasil= "Mohon Maaf, \n\nHasil test yang telah kamu lakukan menunjukkan Kamu dalam kondisi mental yang sedang tidak baik. Kami menyarankan anda untuk melakukan serangkaian layanan seperti Meditasi agar lebih menenagkan anda dan juga melakukan Konseling dengan seorang Profesional\n";
        }

//        Bundle extras = getIntent().getExtras();
//        hasil = extras.getString(KEY_RESULT);
        tvHasil.setText(hasil);
    }

    public void masukHome(View view) {
        Intent intent = new Intent(HasilTestActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
