package com.example.saiko.tesPsikolog;

import android.provider.ContactsContract;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class DataKesimpulan implements Serializable {
    private int kesimpulan;
    private String key;

    public DataKesimpulan(){

    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public int getKesimpulan(){
        return kesimpulan;
    }

    public void setKesimpulan(int kesimpulan){
        this.kesimpulan = kesimpulan;
    }

    public String toString(){
        return " " + kesimpulan;
    }

    public DataKesimpulan(int kesim){
        kesimpulan = kesim;
    }
}
