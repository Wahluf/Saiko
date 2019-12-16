package com.example.saiko.masuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saiko.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DaftarActivity extends AppCompatActivity {
    private EditText inputNama, inputEmail, inputPassword;
    private Button btnSignUp;
    private String nama, email, inputNoHp, inputUsia, inputJenisKel, inputDomisili, uniqueID;

    //Variabel Firebase
    private FirebaseAuth auth;
    private DatabaseReference db, dbR;
    private FirebaseDatabase db1;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_daftar);

        //Variabel Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        //Variabel kolom inputan
        btnSignUp = (Button) findViewById(R.id.btn_daftar);
        inputNama = (EditText) findViewById(R.id.et_nama);
        inputEmail = (EditText) findViewById(R.id.et_email);
        inputPassword = (EditText) findViewById(R.id.et_kata_sandi);
        inputNoHp = "";
        inputUsia = "";
        inputJenisKel = "";
        inputDomisili = "";
        // progressBar = (ProgressBar)
        db1 = FirebaseDatabase.getInstance();
        dbR = db1.getReference("Data Pengguna");
//        uniqueID = dbR;

//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
//        final String uniqueID = user.getUid(); //Null, bikin error :(


        //Saat tombol SignUp ditekan
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nama = inputNama.getText().toString().trim();
                email = inputEmail.getText().toString().trim();
                String emailu = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                //Error handler
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Mohon masukkan E-mail Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Mohon masukkan kata sandi Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(), "Kata sandi terlalu pendek, mohon masukkan kata sandi yang benar", Toast.LENGTH_SHORT).show();
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);

                //Bikin akun
                auth.createUserWithEmailAndPassword(emailu, password).addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            Toast.makeText(DaftarActivity.this, "Pendaftaran gagal", Toast.LENGTH_SHORT).show();
                        } else {
//                            submitData(new DataRegis(nama, email, inputNoHp, inputUsia, inputJenisKel, inputDomisili/*, uniqueID*/));
                            Toast.makeText(DaftarActivity.this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DaftarActivity.this, MasukActivity.class));
                            finish();
                        }
                    }
                });

                auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if(user != null){
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            Map<String, String> map = new HashMap<>();
                            map.put("nama", nama);
                            map.put("email", email);
                            map.put("nilaiTes", "");
                            ref.child("Data Pengguna").child(user.getUid()).setValue(map);
                        }
                    }
                });
            }
        });
    }

    //fungsi submit data registrasi pengguna ke database
    private void submitData(DataRegis data){
        db.child("Data Pengguna").push().setValue(data).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                inputNama.setText("");
                inputEmail.setText("");
                inputPassword.setText("");
//                inputNoHp.setText("");
//                inputUsia.setText("");
//                inputJenisKel.setText("");
//                inputDomisili.setText("");
            }
        });
    }


    //Intent ke tampilan Masuk
    public void launchMasuk(View view) {
        Intent intent = new Intent(DaftarActivity.this, MasukActivity.class);
        startActivity(intent);
    }

}
