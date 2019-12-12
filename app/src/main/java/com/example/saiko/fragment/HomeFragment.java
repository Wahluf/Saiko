package com.example.saiko.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.saiko.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    int counter = 0;
    View rootView;
    private ImageSwitcher image_switch;
    private Handler pic_image_switch_handler;

    //Nama
    private TextView tvNama;

    //Variabel Firebase
    private FirebaseAuth auth;
    private DatabaseReference db;
    private FirebaseDatabase dbf;


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        image_switch = (ImageSwitcher) rootView.findViewById(R.id.is_motivasi);

        image_switch.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });

        pic_image_switch_handler = new Handler(Looper.getMainLooper());

        pic_image_switch_handler.post(new Runnable() {
            @Override
            public void run() {
                switch (counter) {
                    case 0:
                        image_switch.setImageResource(R.mipmap.quotes1);
                        break;
                    case 1:
                        image_switch.setImageResource(R.mipmap.quotes2);
                        break;
                    case 2:
                        image_switch.setImageResource(R.mipmap.quotes3);
                        break;
                    case 3:
                        image_switch.setImageResource(R.mipmap.quotes4);
                        break;
                    case 4:
                        image_switch.setImageResource(R.mipmap.quotes5);
                        break;
                }
                counter += 1;
                if (counter == 5) {
                    counter = 0;
                }
                image_switch.postDelayed(this, 10000);
            }
        });

        init();
        return rootView;
    }

    private void init() {
        //Profile
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Liatin nama
        tvNama = (TextView) rootView.findViewById(R.id.tv_nama_kamu);

        // Baca data
        dbf = FirebaseDatabase.getInstance();
        db = dbf.getReference("Data Pengguna");
        Query query = db.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String Rnama = "" + ds.child("nama").getValue();
                    tvNama.setText("Hallo " + Rnama);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
