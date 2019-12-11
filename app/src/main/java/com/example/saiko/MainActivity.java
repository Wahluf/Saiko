package com.example.saiko;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.saiko.fragment.HomeFragment;
import com.example.saiko.fragment.KonselingFragment;
import com.example.saiko.fragment.MeditasiFragment;
import com.example.saiko.fragment.ProfileFragment;
import com.example.saiko.masuk.PilihMasukActivity;
import com.example.saiko.meditasi.SesiMeditasiActivity;
import com.example.saiko.profile.EditProfileActivity;
import com.example.saiko.profile.TentangActivity;
import com.example.saiko.tesPsikolog.WelcomingTesActivity;
import com.example.saiko.meditasi.MenuMeditasiActivity;
import com.example.saiko.profile.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Bottom Navigation
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        //Image Switcher
        bottomNavigationView = findViewById(R.id.bottomNav);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.homeFragment:
                        fragment = new HomeFragment();
                        break;
                    case R.id.meditasiFragment:
                        fragment = new MeditasiFragment();
                        break;
                    case R.id.konselingFragment:
                        fragment = new KonselingFragment();
                        break;
                    case R.id.profileFragment:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();

                return true;
            }
        });


    }

    public void masukPsikologi(View view) {
        Toast.makeText(getApplicationContext(),"Coming Soon!!!",Toast.LENGTH_SHORT).show();
    }

    public void testPsikolog(View view) {
        Intent intent = new Intent(this, WelcomingTesActivity.class);
        startActivity(intent);
    }

    public void masukMeditasi(View view) {
        Intent intent = new Intent(this, MenuMeditasiActivity.class);
        startActivity(intent);
    }


    //Meditasi
    public void masukSesi(View view) {
        Intent intent = new Intent(this, SesiMeditasiActivity.class);
        startActivity(intent);
    }

    public void masukTunggu(View view) {
        Toast.makeText(getApplicationContext(),"Mohon Maaf untuk sesi ini, Anda harus berlangganan",Toast.LENGTH_SHORT).show();
    }

    //Profile
    public void masukEditProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void masukBerlangganan(View view) {
        Toast.makeText(getApplicationContext(),"Coming Soon!!!",Toast.LENGTH_SHORT).show();
    }

    public void menujuTentang(View view) {
        Intent intent = new Intent(this, TentangActivity.class);
        startActivity(intent);

    }

    public void masukKonselingGratis(View view) {
        Intent intent = new Intent(this, com.example.saiko.konseling.KonselingUtamaActivity.class);
        startActivity(intent);
    }
}
