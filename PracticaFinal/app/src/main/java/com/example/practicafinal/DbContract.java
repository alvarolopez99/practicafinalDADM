package com.example.practicafinal;

import android.provider.BaseColumns;

class DbContract {

    /*static final String SQL_CREATE_ENTRIES_RANKING =
            "CREATE TABLE " + DbEntry.TABLE_NAME_RANKING + " (" +
                    DbEntry._ID + " INTEGER PRIMARY KEY," +
                    DbEntry.COLUMN_NAME_NOMBRE + " TEXT," +
                    DbEntry.COLUMN_NAME_ACIERTOS + " INTEGER," +
                    DbEntry.COLUMN_NAME_TIEMPO + " TEXT)";*/

    static final String SQL_CREATE_ENTRIES_PERFILES =
            "CREATE TABLE " + DbEntry.TABLE_NAME_PERFILES + " (" +
                    DbEntry._ID + " INTEGER PRIMARY KEY," +
                    DbEntry.COLUMN_NAME_FOTO + " TEXT," +
                    DbEntry.COLUMN_NAME_ALIAS + " TEXT UNIQUE," +
                    DbEntry.COLUMN_NAME_FECHAULTIMA + " TEXT," +
                    DbEntry.COLUMN_NAME_NUMPARTIDAS + " TEXT," +
                    DbEntry.COLUMN_NAME_MAXACIERTOS + " INTEGER)";

    /*static final String SQL_DELETE_ENTRIES_RANKING =
            "DROP TABLE IF EXISTS " + DbEntry.TABLE_NAME_RANKING;*/

    static final String SQL_DELETE_ENTRIES_PERFILES =
            "DROP TABLE IF EXISTS " + DbEntry.TABLE_NAME_PERFILES;

    private DbContract() {}

    static class DbEntry implements BaseColumns {
        /*static final String TABLE_NAME_RANKING = "ranking";
        static final String COLUMN_NAME_NOMBRE = "nombre";
        static final String COLUMN_NAME_ACIERTOS = "aciertos";
        static final String COLUMN_NAME_TIEMPO = "tiempo";*/

        static final String TABLE_NAME_PERFILES = "perfiles";
        static final String COLUMN_NAME_FOTO = "foto";
        static final String COLUMN_NAME_FECHAULTIMA = "fechaultima";
        static final String COLUMN_NAME_ALIAS = "alias";
        static final String COLUMN_NAME_MAXACIERTOS = "maxaciertos";
        static final String COLUMN_NAME_NUMPARTIDAS = "numpartidas";
    }
}
