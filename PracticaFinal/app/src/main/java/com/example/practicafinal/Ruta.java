package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.practicafinal.MapsActivity.agregarMarcador;

public class Ruta extends AppCompatActivity implements View.OnClickListener {

    Button openMap;
    Button check;
    static Double[] ubicacionesRuta1 = {42.6036635, -5.6124109,40.4167, -3.70256,40.4167, -3.50256};
    public int localizacionActual=0;

    public static int rutaSeleccionada=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);



        openMap = findViewById(R.id.openMap);

        openMap.setOnClickListener(this);

        check = findViewById(R.id.checkRes);

        check.setOnClickListener(this);

       // agregarMarcador(ubicacionesRuta1[localizacionActual],ubicacionesRuta1[(localizacionActual+1)]);

    }


    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.openMap:

                Intent mapa = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapa);


            case R.id.checkRes:
               localizacionActual = localizacionActual + 2;
               agregarMarcador(ubicacionesRuta1[localizacionActual],ubicacionesRuta1[(localizacionActual+1)]);



        }
    }
}