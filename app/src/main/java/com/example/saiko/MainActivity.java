package com.example.saiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.saiko.tesPsikolog.WelcomingTesActivity;
import com.example.saiko.meditasi.MenuMeditasiActivity;
import com.example.saiko.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity {

    //Bagian Image Switcher
    private ImageView previousbtn, nextbtn;
    private ImageSwitcher imgsw;
    private int[] images = {R.mipmap.quotes1, R.mipmap.quotes2, R.mipmap.quotes3, R.mipmap.quotes4, R.mipmap.quotes5};
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        //Image Switcher
        previousbtn = findViewById(R.id.iv_previous);
        nextbtn = findViewById(R.id.iv_next);
        imgsw = findViewById(R.id.is_motivasi);
        imgsw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgVw= new ImageView(MainActivity.this);
                imgVw.setImageResource(images[position]);
                return imgVw;
            }
        });
        imgsw.setInAnimation(this, android.R.anim.slide_in_left);
        imgsw.setOutAnimation(this, android.R.anim.slide_out_right);
        previousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>0)
                    position--;
                else if(position<0)
                    position = 0;
                imgsw.setImageResource(images[position]);
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<images.length)
                    position++;
                if(position>=images.length)
                    position = images.length-1;
                imgsw.setImageResource(images[position]);
            }
        });

    }


    public void masukProfile(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void testPsikolog(View view) {
        Intent intent = new Intent(MainActivity.this, WelcomingTesActivity.class);
        startActivity(intent);
    }

    public void masukMeditasi(View view) {
        Intent intent = new Intent(MainActivity.this, MenuMeditasiActivity.class);
        startActivity(intent);
    }
}
