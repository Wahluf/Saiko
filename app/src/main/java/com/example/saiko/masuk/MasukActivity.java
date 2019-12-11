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

import com.example.saiko.MainActivity;
import com.example.saiko.R;
import com.example.saiko.profile.EditProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MasukActivity extends AppCompatActivity {
    private EditText inputEmail, inputPass;
    private FirebaseAuth auth;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_masuk);

        auth = FirebaseAuth.getInstance();

        inputEmail = (EditText) findViewById(R.id.et_email);
        inputPass = (EditText) findViewById(R.id.et_kata_sandi);
        btnLogin = (Button) findViewById(R.id.btn_masuk);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String pass = inputPass.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Mohon masukkan E-mail Anda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan kata sandi Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Autentikasi Pengguna
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(MasukActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (pass.length() < 6) {
                                inputPass.setError("Kata sandi terlalu pendek");
                            } else {
                                Toast.makeText(MasukActivity.this, ("E-mail atau kata sandi yang dimasukkan salah"), Toast.LENGTH_LONG).show();
                            }
                        } else if (task.isSuccessful()){
                            Intent intent = new Intent(MasukActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }

    //Intent ke tampilan Daftar
    public void launchDaftar(View view) {
        Intent intent = new Intent(MasukActivity.this, DaftarActivity.class);
        startActivity(intent);
    }

    public void launchMainMenu(View view) {
        Intent intent = new Intent(MasukActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
