package com.example.saiko.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saiko.R;
import com.example.saiko.masuk.DataRegis;
import com.example.saiko.masuk.PilihMasukActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    //Variabel Tombol" dll
    private Button btnHapusAkun, btnLogOut;
    private TextView tvNama, tvEmail;

    //Variabel Firebase
    private FirebaseAuth auth;
    private DatabaseReference db;
    private FirebaseDatabase dbf;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_profile);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Hapus akun
        btnHapusAkun = (Button) findViewById(R.id.btn_hapus_akun);
        btnHapusAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user != null){
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ProfileActivity.this, "Akun Anda berhasil dihapus", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ProfileActivity.this, PilihMasukActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });

        //Sign Out
        btnLogOut = (Button) findViewById(R.id.btn_keluar);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(ProfileActivity.this, PilihMasukActivity.class));
                finish();
            }
        });

        //Liatin nama sama email
        tvNama = (TextView) findViewById(R.id.tv_nama_pengguna);
        tvEmail = (TextView) findViewById(R.id.tv_email_pengguna);

        // Baca data
        dbf = FirebaseDatabase.getInstance();
        db = dbf.getReference("Data Pengguna");
        Query query = db.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String Rnama = "" + ds.child("nama").getValue();
                    String Remail = "" + ds.child("email").getValue();

                    tvNama.setText(Rnama);
                    tvEmail.setText(Remail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
