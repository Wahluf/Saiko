package com.example.saiko.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saiko.MainActivity;
import com.example.saiko.R;
import com.example.saiko.masuk.DataRegis;
import com.example.saiko.masuk.PilihMasukActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {
    //Variabel Tombol" dll
    private Button btnHapusAkun, btnLogOut;
    private TextView tvNama, tvEmail;
    private ImageView ivPhoto;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    //Variabel Firebase
    private FirebaseAuth auth;
    private DatabaseReference db, dbR;
    private FirebaseDatabase dbf, db1;
    private FirebaseUser user;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_profile);

        //get firebase auth instance
        db1 = FirebaseDatabase.getInstance();
        dbR = db1.getReference("Data Pengguna");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Inisialisasi Views
        ivPhoto.bringToFront();
        ivPhoto = (ImageView) findViewById(R.id.img_item_profil_picture);

        //Inisialisasi storageFirebase
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

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
                                dbR.child(user.getUid()).updateChildren(null);
//                                FirebaseDatabase.getInstance().getReference().child("Data Pengguna").child(user.getUid()).removeValue();
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

        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
                //uploadImage();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivPhoto.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
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
