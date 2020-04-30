package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton btnNuev;
    ImageButton btnEdit;
    ImageButton btnSalir;
    ImageButton btnUso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //Poner el icono

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher1);


        btnNuev = (ImageButton) findViewById(R.id.btnNuevo);
        btnEdit = (ImageButton) findViewById(R.id.btnListar);
        btnUso = (ImageButton)findViewById(R.id.btnUso);
        btnSalir = (ImageButton) findViewById(R.id.btnSalir);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listar = new Intent(Menu.this, Lista.class);
                startActivity(listar);
            }
        });
        btnNuev.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent nuevo = new Intent(Menu.this, Nuevo_Registro.class);
                                           startActivity(nuevo);
                                       }
                                   }


        );

        btnUso.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent nuevo = new Intent(Menu.this, Uso.class);
                                           startActivity(nuevo);
                                       }
                                   }


        );

        btnSalir.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

         finish();
         Intent in = new Intent(Intent.ACTION_MAIN);
         in.addCategory(Intent.CATEGORY_HOME);
         in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         startActivity(in);
                                        }
                                    }
        );
    }
}