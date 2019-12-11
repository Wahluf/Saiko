package com.example.saiko.konseling;

import com.example.saiko.R;
import com.example.saiko.R;

import java.util.ArrayList;

public class KonselingData {

    private static int[] Foto = {
            R.mipmap.uii,
            R.mipmap.pijar
    };


    private static String[] KonselingName = {
            "Konseling UII",
            "Pijar Psikologi"
    };


    private static String[] Kategori = {
            "Konseling Mahasiswa",
            "Konseling Umum"
    };


    private static String[] Alamat = {
            "Jl. Kaliurang No.Km. 14,5, Besi, Umbulmartani, Kec. Ngemplak, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55584",
            "Innovative Academy Hub, Bulaksumur H6, UGM, Sleman, D.I. Yogyakarta 55281"
    };


    private static String[] Deskripsi = {
            "Layanan konseling mahasiswa  ini merupakan layanan yang dapat diakses oleh seluruh mahasiswa UII tanpa dipungut biaya. Terdiri dari Layanan Konseling Sebaya dan Layanan Konseling Profesional.",
            "Pijar Psikologi hadir sebagai sarana edukasi mengenai isu-isu kesehatan mental dan psikologi yang mudah diakses. Tujuan kami sederhana. Dengan adanya pengetahuan, maka orang akan dengan mudah menyadari. Dengan timbulnya kesadaran, maka stigma negatif mengenai permasalahan psikologis dapat dikurangi. Harapan kami, mencari bantuan psikologis bukan lagi hal yang perlu ditakuti."
    };


    private static String[] WaktuLayanan = {
            "Jam Kantor",
            "Senin s.d. Jumat, pukul 09.00-17.00 WIB"
    };


    private static String[] NomorLayanan = {
            "082285126556",
            "pijarpsikologi.org"
    };


    private static String[] DaftarKonselorAhli = {
            "- Endah Puspita Sari, S.Psi., M.Si. \n- Fani Eka Nurtjahjo, S.Psi., M.Psi., Psi.\n- Hariz Enggar Wijaya, S.Psi., M.Psi.\n- Hazhira Qudsyi, S.Psi., M.A.\n- Libbie Annatagia, S.Psi., M.Psi.\n- M. Novvaliant Filsuf Tasaufi, S.Psi.\n- Nita Trimulyaningsih, S.Psi., M.Psi.\n- Nur Widiasmara, S.Psi., M.Psi., Psi.\n- Resnia Novitasari, S.Psi., M.A.",
            "Team Psikolog dari Pijar Psikologi"
    };

    private static String[] website = {
            "https://kemahasiswaan.uii.ac.id/layanan/konseling-mahasiswa/",
    };


    //Array list untuk menampilkan data yang telah dibuat diatas
    public static ArrayList<Konseling> getListData() {
        ArrayList<Konseling> list = new ArrayList<>();
        for (int i = 0; i < KonselingName.length; i++) {
            Konseling konseling = new Konseling();
            konseling.setFoto(Foto[i]);
            konseling.setName(KonselingName[i]);
            konseling.setKategori(Kategori[i]);
            konseling.setLokasi(Alamat[i]);
            konseling.setDetail(Deskripsi[i]);
            konseling.setWaktuLayanan(WaktuLayanan[i]);
            konseling.setNomorLayanan(NomorLayanan[i]);
            konseling.setDaftarKonselor(DaftarKonselorAhli[i]);
//            konseling.setWebsite(website[i]);
            list.add(konseling);
        }
        return list;
    }
}
