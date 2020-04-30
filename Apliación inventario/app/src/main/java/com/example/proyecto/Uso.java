package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Uso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uso);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher1);




    }
}
