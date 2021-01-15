package com.example.practicafinal;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btn_Satelite;
    private Button btn_Hibrido;
    private Button btn_Normal;
    private Button btn_Terreno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        btn_Satelite = (Button) findViewById(R.id.id_Satelite);
        btn_Hibrido = (Button) findViewById(R.id.id_Hibrido);
        btn_Normal = (Button) findViewById(R.id.id_Normal);
        btn_Terreno = (Button) findViewById(R.id.id_Terreno);

        btn_Satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                addMarker(mMap);
            }
        });
        btn_Hibrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                addMarker(mMap);
            }
        });
        btn_Normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                addMarker(mMap);
            }
        });
        btn_Terreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                addMarker(mMap);
            }
        });




    }

    public void addMarker (GoogleMap googleMap){



        mMap = googleMap;
        final LatLng punto1 = new LatLng(42.6036635,-5.6124109);
        mMap.addMarker(new MarkerOptions().position(punto1).title("León").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE) ));

        final LatLng punto2 = new LatLng(40.4167, -3.70256);
        mMap.addMarker(new MarkerOptions().position(punto2).title("Madrid"));

        final LatLng punto3 = new LatLng(40.4167, -3.50256);
        mMap.addMarker(new MarkerOptions().position(punto3).title("Madrid"));

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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



        // Add a marker in Sydney and move the camera
        LatLng madrid = new LatLng(40.4167, -3.70256);
        mMap.addMarker(new MarkerOptions().position(madrid).title("Marcador en Madrid"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(madrid));

    }
}