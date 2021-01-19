package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.practicafinal.MapsActivity.agregarMarcador;
import static com.example.practicafinal.MapsActivity.mMap;


public class Ruta extends AppCompatActivity implements View.OnClickListener {

    public static boolean next = false;
    public static Intent mapa;
    Button openMap;
    Button check;

    public static int localizacionActual=0;

    public static int rutaSeleccionada=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);



        openMap = findViewById(R.id.openMap);

        openMap.setOnClickListener(this);

        check = findViewById(R.id.checkRes);

        check.setOnClickListener(this);

         mapa = new Intent(getApplicationContext(), MapsActivity.class);
       // agregarMarcador(ubicacionesRuta1[localizacionActual],ubicacionesRuta1[(localizacionActual+1)]);

    }


    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.openMap:

                startActivity(mapa);
                break;

            case R.id.checkRes:

                localizacionActual = localizacionActual + 2;
                break;


        }
    }
}