package com.example.practicafinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.example.practicafinal.MapsActivity.agregarMarcador;
import static com.example.practicafinal.MapsActivity.mMap;
import static com.example.practicafinal.MapsActivity.ubicaciones;


public class Ruta extends AppCompatActivity implements View.OnClickListener {


    static String[] all_questions,parts;;
    public static boolean next = false;
    public static Intent mapa;
    public static int TOTAL_QUESTIONS=5;
    public static int CURRENT_QUESTION=0;
    private ImageView Escaner;
    ImageView openMap;
    ImageView check;
    EditText answer;
    private TextView textQuestion;
    private TextView textLocation;
    WebView Web;


    public static int localizacionActual=0;
    public static int rutaSeleccionada=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);

        Web = (WebView) findViewById(R.id.webView);
        Web.setWebViewClient(new WebViewClient());

        WebSettings webSettings = Web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        answer = (EditText) findViewById(R.id.EditText);
        textQuestion = (TextView) findViewById(R.id.textQuestionUI);
        textLocation = (TextView) findViewById(R.id.textLocation);

        Escaner = findViewById(R.id.escaner);

        Escaner.setOnClickListener(this);

        openMap = findViewById(R.id.openMap);

        openMap.setOnClickListener(this);

        check = findViewById(R.id.checkRes);

        check.setOnClickListener(this);

        all_questions = getResources().getStringArray(R.array.all_questions);

        mapa = new Intent(getApplicationContext(), MapsActivity.class);

        String Q = all_questions[CURRENT_QUESTION];
        parts = Q.split(";");
        textQuestion.setText(parts[0]);
        textLocation.setText("LOCALIZACIÓN "+ubicaciones[CURRENT_QUESTION]);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String url = ""+result.getContents();
        Web.loadUrl(""+url);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){

            case R.id.openMap:

                startActivity(mapa);
                break;

            case R.id.escaner:
                new IntentIntegrator(Ruta.this).initiateScan();
                break;

            case R.id.checkRes:

                String respuesta = answer.getText().toString();

                CURRENT_QUESTION++;
                if(respuesta.equals(parts[1])&&CURRENT_QUESTION<TOTAL_QUESTIONS) {
                    textLocation.setText("LOCALIZACIÓN "+ubicaciones[CURRENT_QUESTION]);
                    String Q = all_questions[CURRENT_QUESTION];
                    parts = Q.split(";");
                    textQuestion.setText(parts[0]);
                    answer.setText("");
                    localizacionActual = localizacionActual + 2;
                }else if (CURRENT_QUESTION == TOTAL_QUESTIONS){

                    textLocation.setText("FINAL");
                    Intent ar = new Intent(this,ar.class);    //Actividades
                    startActivity(ar);                    //REALIDAD VIRTUAL
                }
                break;
        }
    }



}