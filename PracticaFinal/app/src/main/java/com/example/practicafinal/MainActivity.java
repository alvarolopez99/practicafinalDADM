package com.example.practicafinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.google.zxing.integration.android.IntentIntegrator;

/*
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static TextView tBienvenida;


    ImageView iniciarSesion;
    ImageView btn_Perfiles;

    //AR
    private Button btn_ar;

    //Variables para perfiles
    public static String nombreUsuario = "Anónimo";
    public static int maxAciertos;
    public static int numPartidas;
    public Item itemseleccionado;
    public static String imagen;

    public static final String PREFS_NIVEL = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarSesion = (ImageView) findViewById(R.id.inicioSesion);
        btn_Perfiles = (ImageView) findViewById(R.id.btn_Perfiles);
        tBienvenida = (TextView) findViewById(R.id.tBienvenida);
        if(!nombreUsuario.equalsIgnoreCase("Anónimo")){
            tBienvenida.setText("¡Bienvenido " + nombreUsuario);
        }

        SharedPreferences savedInfo = getSharedPreferences(PREFS_NIVEL, 0);
        nombreUsuario = savedInfo.getString("nombre", "Anónimo");
        setNombreUsuario(nombreUsuario);

        iniciarSesion.setOnClickListener(this);
        btn_Perfiles.setOnClickListener(this);


    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences savedInfo = getSharedPreferences(PREFS_NIVEL, 0);
        SharedPreferences.Editor editor = savedInfo.edit();
        editor.putString("nombre", nombreUsuario);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
    }


    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.inicioSesion:
                Intent Selector = new Intent(this,Selector.class);    //Actividades
                startActivity(Selector);
                break;

            case R.id.btn_Perfiles:
                Intent perfiles = new Intent(this, Perfiles.class);
                startActivity(perfiles);
                break;
        }
    }

    public void setNombreUsuario(String s){
        nombreUsuario = s;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public String getImagen() { return imagen;}

    public void setImagen(String bm) { imagen = bm; }
}