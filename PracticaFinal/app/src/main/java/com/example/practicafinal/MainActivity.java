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

    private Button Escaner;
    private TextView textView;
    WebView view;
    Button btn_openMap;
    Button btn_Temperatura;
    public static TextView tBienvenida;


    ImageView iniciarSesion;
    ImageView btn_Perfiles;



    String txtLatInicio ="4.543986";
    String txtLongInicio ="-75.666736";
    String txtLatFinal ="4.540026";
    String txtLongFinal ="-75.665479";

    //Podo
    private Sensor contadorPasos;
    private SensorManager manejadorSensor;
    private TextView Texto_Pasos;
    private boolean existeSensor;
    int N_Pasos = 0;

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

        //view.getSettings().setJavaScriptEnabled(true);

        /*btn_openMap = (Button) findViewById(R.id.btn_openMap);
        btn_ar = (Button) findViewById(R.id.arbutton);
        btn_Temperatura = (Button) findViewById(R.id.btn_Temperatura);
        Escaner = findViewById(R.id.escaner);*/

        iniciarSesion = (ImageView) findViewById(R.id.inicioSesion);
        btn_Perfiles = (ImageView) findViewById(R.id.btn_Perfiles);
        tBienvenida = (TextView) findViewById(R.id.tBienvenida);
        if(!nombreUsuario.equalsIgnoreCase("Anónimo")){
            tBienvenida.setText("¡Bienvenido " + nombreUsuario);
        }

        SharedPreferences savedInfo = getSharedPreferences(PREFS_NIVEL, 0);
        nombreUsuario = savedInfo.getString("nombre", "Anónimo");
        setNombreUsuario(nombreUsuario);


        /*btn_openMap.setOnClickListener(this);
        Escaner.setOnClickListener(this);
        btn_ar.setOnClickListener(this);
        btn_Temperatura.setOnClickListener(this);
        */

        iniciarSesion.setOnClickListener(this);
        btn_Perfiles.setOnClickListener(this);

        //view = (WebView) findViewById(R.id.web);

        /*Texto_Pasos = findViewById(R.id.id_Texto_Pasos);
        /manejadorSensor = (SensorManager) getSystemService(SENSOR_SERVICE);


        if(manejadorSensor.getDefaultSensor((Sensor.TYPE_STEP_COUNTER)) != null) {
            contadorPasos = manejadorSensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            Texto_Pasos.setText("Sensor de pasos soportado");

        }
        else {
            Texto_Pasos.setText("Sensor de pasos no soportado");
        }*/


    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences savedInfo = getSharedPreferences(PREFS_NIVEL, 0);
        SharedPreferences.Editor editor = savedInfo.edit();
        editor.putString("nombre", nombreUsuario);
    }


    /*@Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == contadorPasos) {
            N_Pasos = (int) event.values[0];
            Texto_Pasos.setText(String.valueOf(N_Pasos));
        }
    }*/

    /*@Override
    public void onResume() {
        super.onResume();
        if(manejadorSensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            manejadorSensor.registerListener(this, contadorPasos, manejadorSensor.SENSOR_DELAY_NORMAL);
        }
    }*/

    /*@Override
    public void onPause() {
        super.onPause();
        if(manejadorSensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            manejadorSensor.unregisterListener(this, contadorPasos);
        }
    }*/

/*

    private void webServiceObtenerRuta(String latitudInicial, String longitudInicial, String latitudFinal, String longitudFinal) {

        String url="https://maps.googleapis.com/maps/api/directions/json?origin="+latitudInicial+","+longitudInicial
                +"&destination="+latitudFinal+","+longitudFinal;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Este método PARSEA el JSONObject que retorna del API de Rutas de Google devolviendo
                //una lista del lista de HashMap Strings con el listado de Coordenadas de Lat y Long,
                //con la cual se podrá dibujar pollinas que describan la ruta entre 2 puntos.
                JSONArray jRoutes = null;
                JSONArray jLegs = null;
                JSONArray jSteps = null;

                try {

                    jRoutes = response.getJSONArray("routes");

                     Traversing all routes
                    for(int i=0;i<jRoutes.length();i++){
                        jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                        List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();

                         Traversing all legs
                        for(int j=0;j<jLegs.length();j++){
                            jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                          Traversing all steps
                            for(int k=0;k<jSteps.length();k++){
                                String polyline = "";
                                polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                                List<LatLng> list = decodePoly(polyline);

                               Traversing all points
                            //    for(int l=0;l<list.size();l++){
                             //       HashMap<String, String> hm = new HashMap<String, String>();
                               //     hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
                                 //   hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
                                   // path.add(hm);
                                }
                            }
                            Utilidades.routes.add(path);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                Log.d("ERROR: ", error.toString());
            }
        }
        );

        request.add(jsonObjectRequest);
    }

    public List<List<HashMap<String,String>>> parse(JSONObject jObject){
        //Este método PARSEA el JSONObject que retorna del API de Rutas de Google devolviendo
        //una lista del lista de HashMap Strings con el listado de Coordenadas de Lat y Long,
        //con la cual se podrá dibujar pollinas que describan la ruta entre 2 puntos.
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;

        try {

            jRoutes = jObject.getJSONArray("routes");

            /** Traversing all routes
            for(int i=0;i<jRoutes.length();i++){
                jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                List<HashMap<String, String>> path = new ArrayList<HashMap<String, String>>();

                /** Traversing all legs
                for(int j=0;j<jLegs.length();j++){
                    jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");

                    /** Traversing all steps
                    for(int k=0;k<jSteps.length();k++){
                        String polyline = "";
                        polyline = (String)((JSONObject)((JSONObject)jSteps.get(k)).get("polyline")).get("points");
                        List<LatLng> list = decodePoly(polyline);

                        /** Traversing all points
                      /*  for(int l=0;l<list.size();l++){
                            HashMap<String, String> hm = new HashMap<String, String>();
                            hm.put("lat", Double.toString(((LatLng)list.get(l)).latitude) );
                            hm.put("lng", Double.toString(((LatLng)list.get(l)).longitude) );
                            path.add(hm);
                        }*//*
                    }
                    Utilidades.routes.add(path);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
        }
        return Utilidades.routes;
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
    }
/*
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result!=null){

            textView.setText("result: "  + result.getContents());


        }
        String url = ""+result.getContents();
        view.loadUrl(""+url);
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        //startActivity(i);
view.setWebViewClient(new WebViewClient(){
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //return super.shouldOverrideUrlLoading(view, url);
        return false;
    }
});*/



    @Override
    public void onClick(View v){
        switch(v.getId()){
            /*case R.id.btn_openMap:
                Intent mapa = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapa);
                break;
*/
            case R.id.inicioSesion:
                Intent Selector = new Intent(this,Selector.class);    //Actividades
                startActivity(Selector);
                break;


            /*case R.id.escaner:
                new IntentIntegrator(MainActivity.this).initiateScan();
                break;

            case R.id.arbutton:
                Intent ar = new Intent(this,ar.class);    //Actividades
                startActivity(ar);
                break;

            case R.id.btn_Temperatura:
                Intent temp = new Intent(this, Temperature.class);
                startActivity(temp);
                break;
                */
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


    public static int getMaxAciertos() {
        return maxAciertos;
    }

    public static void setMaxAciertos(int maxAciertos) {
        MainActivity.maxAciertos = maxAciertos;
    }

    public static int getNumPartidas() {
        return numPartidas;
    }

    public static void setNumPartidas(int numPartidas) {
        MainActivity.numPartidas = numPartidas;
    }

    public String getImagen() { return imagen;}

    public void setImagen(String bm) { imagen = bm; }
}