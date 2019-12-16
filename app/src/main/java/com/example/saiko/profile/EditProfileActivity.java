package com.example.saiko.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.saiko.MainActivity;
import com.example.saiko.R;
import com.example.saiko.masuk.DataRegis;
import com.example.saiko.meditasi.MenuMeditasiActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import java.security.Key;

public class EditProfileActivity extends AppCompatActivity {
    //Variabel tombol dll
    private EditText kNama, kEmail, kNoHp, kUsia, kDomisili;
    private RadioButton jenisKel;
    private RadioGroup jenisKelGroup;
    private Button btnSimpan;
    private String jenisKelamin, email, nama, noHp, usia, domisili;
    private String uidAsli;

    //Variabel Firebase
    //private FirebaseAuth auth;
    //private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference dbNama, dbEmail, dbNoHp, dbUsia, dbJenisKel, dbDomisili;
    private DatabaseReference db, dbu, dbR1;
    private FirebaseDatabase dbf, db1;
    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        final String user_id = user.getUid();

        dbf = FirebaseDatabase.getInstance();
        db = dbf.getReference("Data Pengguna");

        btnSimpan = (Button) findViewById(R.id.btn_simpan);

        kNama = (EditText) findViewById(R.id.et_nama);
        kEmail = (EditText) findViewById(R.id.et_email);
        kNoHp = (EditText) findViewById(R.id.et_no_hp);
        kUsia = (EditText) findViewById(R.id.et_usia);
        kDomisili = (EditText) findViewById(R.id.et_domisili);
        jenisKelGroup = (RadioGroup) findViewById(R.id.jenis_kelamin);
        jenisKelGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                jenisKel = jenisKel.findViewById(i);
                switch (i){
                    case R.id.rb_laki_laki:
                    case R.id.rb_perempuan:
                        jenisKelamin = jenisKel.getText().toString().trim();
                        break;
                    default:
                }
            }
        });

        nama = kNama.getText().toString().trim();
        email = kEmail.getText().toString().trim();
        noHp = kNoHp.getText().toString().trim();
        usia = kUsia.getText().toString().trim();
        domisili = kDomisili.getText().toString().trim();

        // Baca data
        Query query = db.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String Rnama = "" + ds.child("nama").getValue();
                    String Remail = "" + ds.child("email").getValue();
                    String RnoHp = "" + ds.child("noHp").getValue();
                    String Rusia = "" + ds.child("usia").getValue();
                    String Rdomisili = "" + ds.child("domisili").getValue();

                    if(Rnama == null){
                        kNama.setText(" ");
                    } else {
                        kNama.setText(Rnama);
                    }

                    if(Remail == null){
                        kEmail.setText(" ");
                    } else {
                        kEmail.setText(Remail);
                    }

                    if(RnoHp != ""){
                        kNoHp.setText(RnoHp);
                    } else {
                        kNoHp.setText(" ");
                    }

                    if(Rusia != null){
                        kUsia.setText(Rusia);
                    } else {
                        kUsia.setText(" ");
                    }

                    if(Rdomisili != null){
                        kDomisili.setText(Rdomisili);
                    } else {
                        kDomisili.setHint("Mohon diisi");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Tambah data
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = kNama.getText().toString().trim();
                email = kEmail.getText().toString().trim();
                noHp = kNoHp.getText().toString().trim();
                usia = kUsia.getText().toString().trim();
                domisili = kDomisili.getText().toString().trim();

                updateData(new DataUpdate(nama, email, noHp, usia, jenisKelamin, domisili));

                //Error handler
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Mohon masukkan E-mail Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nama)){
                    Toast.makeText(getApplicationContext(), "Mohon masukkan kata sandi Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
//                submitData(new DataRegis(nama, email, noHp, usia, jenisKelamin, domisili));
            }
        });
    }

    private void updateData(DataUpdate data) {
        Query query = db.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
//                    String rUid = "" + ds.child("uid").getValue();
                    String key = child.getKey();

                    uidAsli = key;
//                    uidAsli = rUid;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        db1 = FirebaseDatabase.getInstance();
        dbR1 = db1.getReference("Data Pengguna");
        final String user_id = user.getUid();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("server/saving-data/fireblog");
        DatabaseReference ref = database.getReference("Data Pengguna");
        DatabaseReference usersRef = ref.child(user_id);
        //DatabaseReference hopperRef = usersRef.child(user_id);

//        db1 = FirebaseDatabase.getInstance();
//        dbR1 = db1.getReference("Data Pengguna");
//        String key = dbR1.push().getKey();

        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put("nama", nama);
        hopperUpdates.put("email", email);
        hopperUpdates.put("noHp", noHp);
        hopperUpdates.put("usia", usia);
        hopperUpdates.put("domisili", domisili);
        hopperUpdates.put("jenisKel", jenisKelamin);

        dbR1.child(user.getUid()).updateChildren(hopperUpdates);
//        dbR1.child(uidAsli).setValue(hopperUpdates);
//        hopperRef.child("Data Pengguna").child(user_id).updateChildren(hopperUpdates);

        Toast.makeText(getApplicationContext(), "Perubahan Disimpan", Toast.LENGTH_SHORT).show();
    }
}
