package com.example.practicafinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/*
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Escaner;
    private TextView textView;
    WebView view;
    Button btn_openMap;

    String txtLatInicio ="4.543986";
    String txtLongInicio ="-75.666736";
    String txtLatFinal ="4.540026";
    String txtLongFinal ="-75.665479";

   // JsonObjectRequest jsonObjectRequest;
   // RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view.getSettings().setJavaScriptEnabled(true);
        btn_openMap = (Button) findViewById(R.id.btn_openMap);
        Escaner = findViewById(R.id.escaner);
        textView = findViewById(R.id.textView);
        btn_openMap.setOnClickListener(this);
        Escaner.setOnClickListener(this);
        view = (WebView) findViewById(R.id.web);



    }




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

*/


/*
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
    }



    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_openMap:


              /*  Utilidades.coordenadas.setLatitudInicial(Double.valueOf(txtLatInicio));
                Utilidades.coordenadas.setLongitudInicial(Double.valueOf(txtLongInicio));
                Utilidades.coordenadas.setLatitudFinal(Double.valueOf(txtLatFinal));
                Utilidades.coordenadas.setLongitudFinal(Double.valueOf(txtLongFinal));

                webServiceObtenerRuta(txtLatInicio,txtLongInicio,txtLatFinal,txtLongFinal);
*/

               // Intent miIntent=new Intent(MainActivity.this, MapsActivity.class);
               // startActivity(miIntent);

                Intent mapa = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapa);
                break;

            case R.id.escaner:
                new IntentIntegrator(MainActivity.this).initiateScan();
                break;
        }
    }
}