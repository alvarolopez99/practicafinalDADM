package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Selector extends AppCompatActivity {

    private Spinner selectorPaises;
    private Spinner selectorCiudades;
    private Spinner selectorRutas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        selectorPaises = findViewById(R.id.selectorPaises);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.paises, android.R.layout.simple_spinner_item);
        selectorPaises.setAdapter(adapter1);


        selectorCiudades = findViewById(R.id.selectorCiudades);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.ciudades, android.R.layout.simple_spinner_item);
        selectorCiudades.setAdapter(adapter2);


        selectorRutas = findViewById(R.id.selectorRutas);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.rutas, android.R.layout.simple_spinner_item);
        selectorRutas.setAdapter(adapter3);



        selectorPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if(position==0){

                }else if(position==1){

                }else if(position==2){

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

                }else if(position==1){

                }else if(position==2){

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

                }else if(position==1){

                }else if(position==2){

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







    }


}