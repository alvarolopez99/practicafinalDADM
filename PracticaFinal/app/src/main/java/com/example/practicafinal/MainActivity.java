package com.example.practicafinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Escaner;
    private TextView textView;

    Button btn_openMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_openMap = (Button) findViewById(R.id.btn_openMap);
        Escaner = findViewById(R.id.escaner);
        textView = findViewById(R.id.textView);
        btn_openMap.setOnClickListener(this);
        Escaner.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result!=null){

            textView.setText("result: "  + result.getContents());
        }
    }



    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_openMap:
                Intent mapa = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapa);
                break;

            case R.id.escaner:
                new IntentIntegrator(MainActivity.this).initiateScan();
                break;

        }
    }
}