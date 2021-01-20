package com.example.practicafinal;

import android.graphics.Bitmap;

public class Item {

    private Bitmap fotoPerfil;
    private String alias;
    private String fotoString;

    public Item(Bitmap fotoPerfil, String alias, String fotoString){
        this.fotoPerfil = fotoPerfil;
        this.alias = alias;
        this.fotoString = fotoString;
    }

    public Bitmap getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Bitmap fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }
}
