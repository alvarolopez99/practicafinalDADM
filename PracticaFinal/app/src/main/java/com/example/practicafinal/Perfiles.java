package com.example.practicafinal;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Perfiles extends AppCompatActivity implements View.OnClickListener {
    
    public static ListView listview;
    public static Adaptador adapter;
    public ArrayList<Item> listaItems = new ArrayList<>();
    EditText et;
    Context context;
    TextView placeholder;
    String nombre;
    int numPartidas, maxAciertos;
    static Item item;
    static Bitmap bm;
    static String fotobitmap;
    String foto;
    int fotoEscogidaDefecto;
    MainActivity mainActivity;


    final int PHOTO_RESULT=1;
    int PERMISSION_REQUEST_CODE_READ_EXTERNAL_STORAGE = 100;
    Uri photoURI;
    String currentPhotoPath;

    DbManager dbManager;
    Cursor cursor;

    public static String aux;
    public boolean hasEscogido;

    Bitmap icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);

        requestPermission();

        this.dbManager = new DbManager(this);
        cursor = this.dbManager.getEntriesPerfiles();

        //this.dbManager.deleteAllPerfiles();

        hasEscogido = false;

        listview = findViewById(R.id.idListView_perfiles);
        placeholder = findViewById(R.id.placeholder);

        findViewById(R.id.idImageButton_salirperfil).setOnClickListener(this);
        findViewById(R.id.idButton_añadir).setOnClickListener(this);
        findViewById(R.id.idButton_eliminar).setOnClickListener(this);
        findViewById(R.id.idButton_modificar).setOnClickListener(this);
        findViewById(R.id.idButton_seleccionar).setOnClickListener(this);
        context = this;

        mostrarBaseDatos();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = (Item) adapter.getItem(position);

            }
        });


    }

    public ArrayList<Item> GetArrayItems(){
        return listaItems;
    }

    public Adaptador getAdapter(){
        return adapter;
    }


    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.idImageButton_salirperfil:
                if(item != null){
                    foto = item.getFotoString();
                    nombre = item.getAlias();
                    maxAciertos = item.getMaxAciertos();
                    numPartidas = item.getNumPartidas();
                    mainActivity.setNombreUsuario(nombre);
                    mainActivity.setMaxAciertos(maxAciertos);
                    mainActivity.setNumPartidas(numPartidas);
                    mainActivity.setImagen(foto);
                    mainActivity.itemseleccionado = item;
                }
                listaItems.clear();
                finish();
                break;
            case R.id.idButton_añadir:
                AlertDialog.Builder builder = new AlertDialog.Builder(Perfiles.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_anadir, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                ImageButton btnCancel = (ImageButton) dialogView.findViewById(R.id.salirescoger);
                et = (EditText) dialogView.findViewById(R.id.idEditText_nombre2);
                ImageButton finalizar = (ImageButton) dialogView.findViewById(R.id.finalizar);
                ImageButton tomarFoto = (ImageButton) dialogView.findViewById(R.id.tomarFoto);
                ImageButton mujer = (ImageButton) dialogView.findViewById(R.id.mujer);
                ImageButton hombre = (ImageButton) dialogView.findViewById(R.id.hombre);
                final TextView info = (TextView) dialogView.findViewById(R.id.info);
                final TextView infofoto = (TextView) dialogView.findViewById(R.id.infofoto);
                final TextView infousuario = (TextView) dialogView.findViewById(R.id.infousuario);

                btnCancel.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hasEscogido = false;
                                alertDialog.dismiss();
                            }
                        }
                );

                finalizar.setOnClickListener(
                        new View.OnClickListener() {

                            DbManager dbManager = new DbManager(getBaseContext());


                            @Override
                            public void onClick(View v) {


                                aux = et.getText().toString();
                                boolean encontrado = false;
                                for(Item i: listaItems){
                                    if(i.getAlias().equalsIgnoreCase(aux)) {
                                        encontrado = true;
                                    }
                                }
                                if(!encontrado && hasEscogido && !aux.equals("")) {
                                    listaItems.add(new Item(bm, aux, null, 0, 0, fotobitmap));
                                    this.dbManager.insertEntryPerfiles(fotobitmap, aux, null, 0, 0);
                                    adapter = new Adaptador(GetArrayItems(), context);
                                    listview.setAdapter(adapter);
                                    hasEscogido = false;
                                    alertDialog.dismiss();
                                } else if(encontrado){
                                    infofoto.setVisibility(View.GONE);
                                    infousuario.setVisibility(View.GONE);
                                    info.setVisibility(View.VISIBLE);
                                } else if(!encontrado && !hasEscogido){
                                    info.setVisibility(View.GONE);
                                    infousuario.setVisibility(View.GONE);
                                    infofoto.setVisibility(View.VISIBLE);
                                } else if(aux.equals("")){
                                    info.setVisibility(View.GONE);
                                    infofoto.setVisibility(View.GONE);
                                    infousuario.setVisibility(View.VISIBLE);
                                }

                            }
                        }
                );

                mujer.setOnClickListener(
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.mujer);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                                byte[] imagen = stream.toByteArray();
                                fotobitmap = Base64.encodeToString(imagen, Base64.DEFAULT);
                                hasEscogido = true;
                            }
                        }
                );

                hombre.setOnClickListener(
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.hombre);
                                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                                bm.compress(Bitmap.CompressFormat.JPEG, 100, stream2);

                                byte[] imagen2 = stream2.toByteArray();
                                fotobitmap = Base64.encodeToString(imagen2, Base64.DEFAULT);
                                hasEscogido = true;
                            }
                        }
                );


                tomarFoto.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                        public void onClick(View v) {
                                dispatchTakePictureIntent();
                    }
                });

                alertDialog.show();
                break;

            case R.id.idButton_eliminar:
                if(item != null) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                    builder2.setTitle("¿Estás seguro que quieres eliminar este perfil?");
                    builder2.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        DbManager dbManager = new DbManager(getBaseContext());

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            listaItems.remove(item);
                            String nombreLocal = item.getAlias();
                            item = null;
                            this.dbManager.deletePerfilesRow(nombreLocal);
                            adapter = new Adaptador(GetArrayItems(), context);
                            listview.setAdapter(adapter);
                        }
                    });
                    builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder2.create();
                    dialog.show();
                }
                break;
            case R.id.idButton_seleccionar:
                if(item != null) {
                    if (!item.getAlias().equals("")) {
                        placeholder.setText("Has seleccionado el perfil: " + item.getAlias());
                        placeholder.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.idButton_modificar:
                if(item != null) {
                    AlertDialog.Builder b = new AlertDialog.Builder(Perfiles.this);
                    ViewGroup vg = findViewById(android.R.id.content);
                    View dv = LayoutInflater.from(v.getContext()).inflate(R.layout.fragment_anadir, vg, false);
                    b.setView(dv);
                    final AlertDialog ad = b.create();
                    ImageButton cancel = (ImageButton) dv.findViewById(R.id.salirescoger);
                    ImageButton finalizar2 = (ImageButton) dv.findViewById(R.id.finalizar);
                    ImageButton tomarFoto2 = (ImageButton) dv.findViewById(R.id.tomarFoto);
                    ImageButton mujer2 = (ImageButton) dv.findViewById(R.id.mujer);
                    ImageButton hombre2 = (ImageButton) dv.findViewById(R.id.hombre);
                    et = dv.findViewById(R.id.idEditText_nombre2);
                    et.setVisibility(View.GONE);
                    TextView tv = dv.findViewById(R.id.textView8);
                    tv.setText("Modificación \n de perfil");


                    cancel.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    hasEscogido = false;
                                    ad.dismiss();
                                }
                            }
                    );

                    finalizar2.setOnClickListener(
                            new View.OnClickListener() {

                                DbManager dbManager = new DbManager(getBaseContext());

                                @Override
                                public void onClick(View v) {
                                    String alias = item.getAlias();
                                    String fu = item.getFechaUltima();
                                    int ma = item.getMaxAciertos();
                                    int nps = item.getNumPartidas();
                                    listaItems.remove(item);
                                    this.dbManager.deletePerfilesRow(alias);
                                    listaItems.add(new Item(bm, alias, fu, ma, nps, fotobitmap));
                                    this.dbManager.insertEntryPerfiles(fotobitmap, alias, fu, ma, nps);
                                    adapter = new Adaptador(GetArrayItems(), context);
                                    listview.setAdapter(adapter);
                                    hasEscogido = false;
                                    ad.dismiss();
                                }
                            }
                    );

                    mujer2.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.mujer);
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                                    byte[] imagen = stream.toByteArray();
                                    fotobitmap = Base64.encodeToString(imagen, Base64.DEFAULT);
                                    hasEscogido = true;
                                }
                            }
                    );

                    hombre2.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.hombre);
                                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                                    bm.compress(Bitmap.CompressFormat.JPEG, 100, stream2);

                                    byte[] imagen2 = stream2.toByteArray();
                                    fotobitmap = Base64.encodeToString(imagen2, Base64.DEFAULT);
                                    hasEscogido = true;
                                }
                            }
                    );

                    tomarFoto2.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dispatchTakePictureIntent();
                                }
                            });

                    ad.show();
                }
                break;
        }
    }

    public void mostrarBaseDatos(){
        int i = 0;
        while(i < cursor.getCount()){

            cursor.moveToPosition(i);
            String photo = cursor.getString(cursor.getColumnIndex("foto"));
            Bitmap bitmap = StringToBitMap(photo);
            String alias = cursor.getString(cursor.getColumnIndex("alias"));
            String lastdate = cursor.getString(cursor.getColumnIndex("fechaultima"));
            int maxaciertos = cursor.getInt(cursor.getColumnIndex("maxaciertos"));
            int numpartidas = cursor.getInt(cursor.getColumnIndex("numpartidas"));
            listaItems.add(new Item(bitmap, alias, lastdate, maxaciertos, numpartidas, photo));
            i++;
        }
        adapter = new Adaptador(GetArrayItems(), context);
        listview.setAdapter(adapter);
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
        String imageFileName = "PHOTO_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(Perfiles.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Perfiles.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }

            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.prac1.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, PHOTO_RESULT);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == PHOTO_RESULT && resultCode == RESULT_OK) {
            try {
                Bitmap croppedBmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoURI));
                bm = Bitmap.createBitmap(croppedBmp, 150, 400, croppedBmp.getWidth() - 300, croppedBmp.getHeight()-700);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                byte[] imagen = stream.toByteArray();
                fotobitmap = Base64.encodeToString(imagen, Base64.DEFAULT);
                hasEscogido = true;
            } catch (FileNotFoundException e) {
            }
        }
    }


}