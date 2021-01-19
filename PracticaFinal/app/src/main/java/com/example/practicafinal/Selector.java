package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static com.example.practicafinal.Ruta.rutaSeleccionada;

public class Selector extends AppCompatActivity implements View.OnClickListener {

    private Spinner selectorPaises;
    private Spinner selectorCiudades;
    private Spinner selectorRutas;
    Button start;
    public static Intent ruta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        ruta = new Intent(getApplicationContext(), Ruta.class);

        selectorPaises = findViewById(R.id.selectorPaises);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.paises, android.R.layout.simple_spinner_item);
        selectorPaises.setAdapter(adapter1);


        selectorCiudades = findViewById(R.id.selectorCiudades);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.ciudades, android.R.layout.simple_spinner_item);
        selectorCiudades.setAdapter(adapter2);


        selectorRutas = findViewById(R.id.selectorRutas);




        start = findViewById(R.id.start);

        start.setOnClickListener(this);



        selectorPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if(position==0){

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        selectorCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if(position==0){

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        selectorRutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if(position==0){
                    rutaSeleccionada=0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







    }



    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.start:
                //Intent ruta = new Intent(this, Ruta.class);    //Actividades
                startActivity(ruta);
                break;

        }
    }
}