package com.example.practicafinal;

import android.graphics.Bitmap;

public class Item {

    private Bitmap fotoPerfil;
    private String alias;
    private String fechaUltima;
    private int maxAciertos;
    private int numPartidas;
    private String fotoString;

    public Item(Bitmap fotoPerfil, String alias, String fechaUltima, int maxAciertos, int numPartidas, String fotoString){
        this.fotoPerfil = fotoPerfil;
        this.alias = alias;
        this.fechaUltima = fechaUltima;
        this.maxAciertos = maxAciertos;
        this.numPartidas = numPartidas;
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

    public String getFechaUltima() {
        return fechaUltima;
    }

    public void setFechaUltima(String fechaUltima) {
        this.fechaUltima = fechaUltima;
    }

    public int getMaxAciertos() {
        return maxAciertos;
    }

    public void setMaxAciertos(int maxAciertos) {
        this.maxAciertos = maxAciertos;
    }

    public int getNumPartidas() {
        return numPartidas;
    }

    public void setNumPartidas(int numPartidas) {
        this.numPartidas = numPartidas;
    }

    public String getFotoString() {
        return fotoString;
    }

    public void setFotoString(String fotoString) {
        this.fotoString = fotoString;
    }
}
