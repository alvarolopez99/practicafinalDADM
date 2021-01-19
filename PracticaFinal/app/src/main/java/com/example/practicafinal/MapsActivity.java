package com.example.practicafinal;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;


import android.content.Context;
import android.content.Intent;


import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.practicafinal.Ruta.CURRENT_QUESTION;
import static com.example.practicafinal.Ruta.TOTAL_QUESTIONS;
import static com.example.practicafinal.Ruta.localizacionActual;
import static com.example.practicafinal.Selector.ruta;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SensorEventListener {


    public static GoogleMap mMap;

    static Double[] ubicacionesRuta1 = {40.41736, -3.68303, 40.41924, -3.69337, 40.41693, -3.70351, 40.41820, -3.70947, 40.41718, -3.71441};
    public static String [] ubicaciones = {"A","B","C","D","E"};
    public int numUbicacion=0;
    private Button btn_Satelite, btn_Hibrido, btn_Normal, btn_Terreno,btn_Back;

    private Button btn_Twitter, btn_Facebook, btn_Whatsapp;

    Marker marcador;
    double lat = 0.0;
    double lng = 0.0;


    //Apartado temperatura
    public TextView tTemperatura;
    private SensorManager sensorManager;
    private Sensor sensorTemperature;
    private boolean isTemperatureSensorAvailable;

    //Apartado brújula
    private Button icono_Orientacion;
    private boolean acelerometroCambiado = false;
    private boolean magneticoCambiado = false;
    private SensorManager manejador_Sensores;
    private Sensor sensor_Acelerometro, sensor_Magnetico;
    private float[] ultimos_Valores_Acelerometro;
    private float[] ultimos_Valores_Magnetico;
    private float[] valores_Rotacion;
    private float[] valores_Orientacion;
    private float rotacion_Actual = 0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //region TEMPERATURA
        tTemperatura = (TextView) findViewById(R.id.tTemperatura);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSensorAvailable = true;
        } else{
            tTemperatura.setText("Temperature sensor is not available");
            isTemperatureSensorAvailable = false;
        }
        //endregion

        //region BRUJULA
        ultimos_Valores_Magnetico = new float[3];
        ultimos_Valores_Acelerometro = new float[3];
        valores_Orientacion = new float[3];
        valores_Rotacion = new float[9];

        icono_Orientacion = (Button) findViewById(R.id.id_Flecha);
        manejador_Sensores = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor_Magnetico = manejador_Sensores.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensor_Acelerometro = manejador_Sensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //endregion

        btn_Satelite = (Button) findViewById(R.id.id_Satelite);
        btn_Hibrido = (Button) findViewById(R.id.id_Hibrido);
        btn_Normal = (Button) findViewById(R.id.id_Normal);
        btn_Terreno = (Button) findViewById(R.id.id_Terreno);


       btn_Twitter = (Button) findViewById(R.id.id_Twitter);
       btn_Facebook = (Button) findViewById(R.id.id_Facebook);
       btn_Whatsapp = (Button) findViewById(R.id.id_WhatsApp);

        btn_Back = (Button) findViewById(R.id.id_Back);



        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ruta);
            }
        });

        btn_Satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                //addMarker(mMap);
                //agregarRuta1(mMap);
            }
        });
        btn_Hibrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                //addMarker(mMap);
            }
        });
        btn_Normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                //addMarker(mMap);
            }
        });
        btn_Terreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                //addMarker(mMap);
            }
        });



        btn_Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "¡Únete a mi búsqueda de Scan It! Nos vemos en las coordenadas "+ubicacionesRuta1[localizacionActual*2]+", "+ubicacionesRuta1[localizacionActual*2+1]);
                intent.setPackage("com.twitter.android");
                startActivity(Intent.createChooser(intent, "Compartir..."));
            }
        });

        btn_Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "¡Únete a mi búsqueda de Scan It! Nos vemos en las coordenadas "+ubicacionesRuta1[localizacionActual*2]+", "+ubicacionesRuta1[localizacionActual*2+1]);
                intent.setPackage("com.facebook.katana");
                startActivity(Intent.createChooser(intent, "Compartir..."));
            }
        });

        btn_Whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "¡Únete a mi búsqueda de Scan It! Nos vemos en las coordenadas "+ubicacionesRuta1[localizacionActual*2]+", "+ubicacionesRuta1[localizacionActual*2+1]);
                intent.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(intent, "Compartir..."));
            }
        });

    }



    public static void agregarMarcador(double lat, double lng, GoogleMap googleMap){


        //final LatLng posicion = new LatLng(lat, lng);
        //googleMap.addMarker(new MarkerOptions().position(posicion));

    }


    public  void agregaPunto1(GoogleMap googleMap) {

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(42.6036635, -5.6124109), new LatLng(40.4167, -3.70256))
                .width(5)
                .color(Color.RED));

        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        final LatLng punto1 = new LatLng(42.6036635, -5.6124109);
        mMap.addMarker(new MarkerOptions().position(punto1).title("León").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

       //  final LatLng punto2 = new LatLng(40.4167, -3.70256);
       // mMap.addMarker(new MarkerOptions().position(punto2).title("Madrid"));

       // final LatLng punto3 = new LatLng(40.4167, -3.50256);
       // mMap.addMarker(new MarkerOptions().position(punto3).title("Madrid"));
    }
    public  void agregaMarcador(double lat, double lng, GoogleMap googleMap, String ubicacion) {

        final LatLng posicion = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(posicion).title(("ubicacion " + ubicacion)));


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i = 0; i<((CURRENT_QUESTION+1)*2); i++){

            agregaMarcador(ubicacionesRuta1[i],ubicacionesRuta1[(i+1)],mMap,ubicaciones[numUbicacion]);
            i++;
            numUbicacion++;
        }



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setMyLocationButtonEnabled(true);



        /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        */





/*
        // Add a marker in Sydney and move the camera
        LatLng madrid = new LatLng(40.4167, -3.70256);
        mMap.addMarker(new MarkerOptions().position(madrid).title("Marcador en Madrid"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));*/







        // Add a marker in Sydney and move the camera

        //    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /////////////
        /*
        LatLng center = null;
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;

        // setUpMapIfNeeded();

        // recorriendo todas las rutas
        for(int i=0;i<Utilidades.routes.size();i++){
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();

            // Obteniendo el detalle de la ruta
            List<HashMap<String, String>> path = Utilidades.routes.get(i);

            // Obteniendo todos los puntos y/o coordenadas de la ruta
            for(int j=0;j<path.size();j++){
                HashMap<String,String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                if (center == null) {
                    //Obtengo la 1ra coordenada para centrar el mapa en la misma.
                    center = new LatLng(lat, lng);
                }
                points.add(position);
            }

            // Agregamos todos los puntos en la ruta al objeto LineOptions
            lineOptions.addAll(points);
            //Definimos el grosor de las Polilíneas
            lineOptions.width(2);
            //Definimos el color de la Polilíneas
            lineOptions.color(Color.BLUE);
        }

        // Dibujamos las Polilineas en el Google Map para cada ruta
        mMap.addPolyline(lineOptions);

        LatLng origen = new LatLng(Utilidades.coordenadas.getLatitudInicial(), Utilidades.coordenadas.getLongitudInicial());
        mMap.addMarker(new MarkerOptions().position(origen).title("Lat: "+Utilidades.coordenadas.getLatitudInicial()+" - Long: "+Utilidades.coordenadas.getLongitudInicial()));

        LatLng destino = new LatLng(Utilidades.coordenadas.getLatitudFinal(), Utilidades.coordenadas.getLongitudFinal());
        mMap.addMarker(new MarkerOptions().position(destino).title("Lat: "+Utilidades.coordenadas.getLatitudFinal()+" - Long: "+Utilidades.coordenadas.getLongitudFinal()));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(center, 15));
        /////////////


         */
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        tTemperatura.setText(sensorEvent.values[0] + " ºC");

        if (sensorEvent.sensor == sensor_Magnetico) {

            System.arraycopy(sensorEvent.values, 0, ultimos_Valores_Magnetico, 0, sensorEvent.values.length);
            magneticoCambiado = true;

        } else if (sensorEvent.sensor == sensor_Acelerometro) {

            System.arraycopy(sensorEvent.values, 0, ultimos_Valores_Acelerometro, 0, sensorEvent.values.length);
            acelerometroCambiado = true;

        }
        if (acelerometroCambiado && magneticoCambiado) {

            SensorManager.getRotationMatrix(valores_Rotacion, null, ultimos_Valores_Acelerometro, ultimos_Valores_Magnetico);
            SensorManager.getOrientation(valores_Rotacion, valores_Orientacion);

            float angulo_Orientacion_Radianes = valores_Orientacion[0];
            float angulo_Orientacion_Grados = (float)(Math.toDegrees(angulo_Orientacion_Radianes)+360)%360;

            RotateAnimation animacion_Rotacion = new RotateAnimation(rotacion_Actual, -angulo_Orientacion_Grados, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            animacion_Rotacion.setDuration(1);
            animacion_Rotacion.setFillAfter(true);

            icono_Orientacion.startAnimation(animacion_Rotacion);
            rotacion_Actual = -angulo_Orientacion_Grados;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(isTemperatureSensorAvailable){
            sensorManager.registerListener(this, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
        manejador_Sensores.registerListener(this, sensor_Acelerometro, SensorManager.SENSOR_DELAY_UI);
        manejador_Sensores.registerListener(this, sensor_Magnetico, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(isTemperatureSensorAvailable){
            sensorManager.unregisterListener(this);
        }
        manejador_Sensores.unregisterListener(this, sensor_Magnetico);
        manejador_Sensores.unregisterListener(this, sensor_Acelerometro);
    }
}