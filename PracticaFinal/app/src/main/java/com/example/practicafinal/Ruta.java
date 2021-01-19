package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.practicafinal.MapsActivity.agregarMarcador;
import static com.example.practicafinal.MapsActivity.mMap;
import static com.example.practicafinal.MapsActivity.ubicaciones;


public class Ruta extends AppCompatActivity implements View.OnClickListener {


    static String[] all_questions,parts;;
    public static boolean next = false;
    public static Intent mapa;
    public int TOTAL_QUESTIONS=5;
    public int CURRENT_QUESTION=0;
    Button openMap;
    Button check;
    EditText answer;
    private TextView textQuestion;
    private TextView textLocation;

    public static int localizacionActual=0;
    public static int rutaSeleccionada=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);

        answer = (EditText) findViewById(R.id.EditText);
        textQuestion = (TextView) findViewById(R.id.textQuestionUI);
        textLocation = (TextView) findViewById(R.id.textLocation);

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
    public void onClick(View v){
        switch(v.getId()){

            case R.id.openMap:

                startActivity(mapa);
                break;

            case R.id.checkRes:

                String respuesta = answer.getText().toString();

                CURRENT_QUESTION++;
                if(respuesta.equals(parts[1])&&CURRENT_QUESTION<TOTAL_QUESTIONS) {
                    textLocation.setText("LOCALIZACIÓN "+ubicaciones[CURRENT_QUESTION]);
                    String Q = all_questions[CURRENT_QUESTION];
                    parts = Q.split(";");
                    textQuestion.setText(parts[0]);
                    localizacionActual = localizacionActual + 2;
                }else if (CURRENT_QUESTION == TOTAL_QUESTIONS){

                    textLocation.setText("FINAL");
                    //REALIDAD VIRTUAL
                }
                break;
        }
    }
}