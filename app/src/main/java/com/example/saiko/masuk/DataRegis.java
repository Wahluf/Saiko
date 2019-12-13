package com.example.saiko.masuk;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class DataRegis implements Serializable {
    private String nama, email, noHp, usia, jenisKel, domisili, UID;
    private String key;

    public DataRegis(){

    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNoHp(){
        return noHp;
    }

    public void setNoHp(String noHp){
        this.noHp = noHp;
    }

    public String getUsia(){
        return usia;
    }

    public void setUsia(String usia){
        this.usia = usia;
    }

    public String getJenisKel(){
        return jenisKel;
    }

    public void setJenisKel(String jenisKel){
        this.jenisKel = jenisKel;
    }

    public String getDomisili(){
        return domisili;
    }

    public void setDomisili(String domisili){
        this.domisili = domisili;
    }

    public String getUID() { return UID; }

    public void setUID(String UID) { this.UID = UID; }

    @Override
    public String toString(){
        return " " + nama + "\n" +
                " " + email + "\n" +
                " " + noHp + "\n" +
                " " + usia + "\n" +
                " " + jenisKel + "\n" +
                " " + domisili + "\n" +
                " " + UID;
    }

    public DataRegis(String nm, String eml, String nohp, String usi4, String jenisk, String dmsli/*, String uid*/){
        nama = nm;
        email = eml;
        noHp = nohp;
        usia = usi4;
        jenisKel = jenisk;
        domisili = dmsli;
//        UID = uid;
    }
}
