package com.example.saiko.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.saiko.MainActivity;
import com.example.saiko.R;
import com.example.saiko.masuk.PilihMasukActivity;
import com.example.saiko.profile.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }


    //Variabel Tombol"
    private Button btnHapusAkun, btnLogOut;
    private TextView tvNama, tvEmail;

    //Variabel Firebase
    private FirebaseAuth auth;
    private DatabaseReference db;
    private FirebaseDatabase dbf;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        init();
        return view;
    }

    private void init() {
        //Profile
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Hapus akun
        btnHapusAkun = (Button) view.findViewById(R.id.btn_hapus_akun);
        btnHapusAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user != null){
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "Akun Anda berhasil dihapus", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), PilihMasukActivity.class));
                                getActivity().finish();
                            }
                        }
                    });
                }
            }
        });

        //Sign Out
        btnLogOut = (Button) view.findViewById(R.id.btn_keluar);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(getActivity(), PilihMasukActivity.class));
                getActivity().finish();
            }
        });

        //Liatin nama sama email
        tvNama = (TextView) view.findViewById(R.id.tv_nama_pengguna);
        tvEmail = (TextView) view.findViewById(R.id.tv_email_pengguna);

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


}
