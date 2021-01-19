package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
/*
public class showPhoto extends AppCompatActivity {

    ImageView photo;
    Uri uri;
    private Button btn_Twitter, btn_Facebook, btn_Whatsapp, btn_menu;
    Intent menuI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        menuI = new Intent(this, MainActivity.class);

        photo = findViewById(R.id.imageViewer);

        Bitmap bmp = BitmapFactory.decodeFile(ar.imagePath.toString());
        photo.setImageBitmap(bmp);

        btn_Twitter = (Button) findViewById(R.id.id_Twitter2);
        btn_Facebook = (Button) findViewById(R.id.id_Facebook2);
        btn_Whatsapp = (Button) findViewById(R.id.id_WhatsApp2);
        btn_menu = (Button) findViewById(R.id.menubtn);

        uri = Uri.parse(ar.imagePath.toString());

        btn_Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "¡He completado la ruta demo de *NOMBREAPP*!");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/png");
                intent.setPackage("com.twitter.android");
                startActivity(Intent.createChooser(intent, "Compartir..."));
            }
        });

        btn_Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "¡He completado la ruta demo de *NOMBREAPP*!");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/png");
                intent.setPackage("com.facebook.katana");
                startActivity(Intent.createChooser(intent, "Compartir..."));
            }
        });

        btn_Whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "¡He completado la ruta demo de *NOMBREAPP*!");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/png");
                intent.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(intent, "Compartir..."));
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(menuI);
            }
        });

    }

}*/