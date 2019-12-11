package com.example.saiko.konseling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.saiko.R;

import java.util.ArrayList;

public class listKonselingData extends RecyclerView.Adapter<listKonselingData.ListViewHolder> {

    //ArrayList untuk menyimpan data provinsi dalam bentuk list
    private ArrayList<Konseling> listKonseling;

    private OnItemClickCallback onItemClickCallback;

    public listKonselingData(ArrayList<Konseling> list) {
        this.listKonseling = list;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Konseling data);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_konseling, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {

        Konseling konseling = listKonseling.get(position);
        Glide.with(holder.itemView.getContext())
                .load(konseling.getFoto())
                .apply(new RequestOptions().override(500, 600))
                .into(holder.imgFoto);
        holder.tvKonseling.setText(konseling.getName());
        holder.tvKategoti.setText(konseling.getKategori());
        holder.tvLokasi.setText(konseling.getLokasi());

        //Trigger saat button detail di klik, dan menampilkan aktivity deskripsi
        holder.cvTombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listKonseling.get(holder.getAdapterPosition()));
            }
        });
    }

    //Menghitung ukuran dari ArrayList
    @Override
    public int getItemCount() {
        return listKonseling.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView tvKategoti, tvLokasi, tvKonseling, tvdeskripsi, tvWaktulayanan, tvNomorLayanan, tvKonselor, tvWebsite;
        CardView cvTombol;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.img_item_profil);
            tvKonseling = itemView.findViewById(R.id.tv_konseling);
            tvKategoti = itemView.findViewById(R.id.tv_kategori);
            tvLokasi = itemView.findViewById(R.id.tv_lokasi);
            cvTombol = itemView.findViewById(R.id.cv_klik);
            tvdeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvWaktulayanan = itemView.findViewById(R.id.tv_waktu_layananan);
            tvNomorLayanan = itemView.findViewById(R.id.tv_nomor);
            tvKonselor = itemView.findViewById(R.id.tv_daftar_konselor);
            //tvWebsite = itemView.findViewById(R.id.tv_website);

        }
    }
}
