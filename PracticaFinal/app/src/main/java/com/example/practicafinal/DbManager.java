package com.example.practicafinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {

    private DbHelper helper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        this.helper = new DbHelper(context);
        this.db = this.helper.getWritableDatabase();
    }

    public void insertEntryPerfiles(String foto, String alias, String fechaultima, int maxaciertos, int numpartidas){
        this.db.insert(DbContract.DbEntry.TABLE_NAME_PERFILES, null,
                this.generateContentValuesPerfiles(foto, alias, fechaultima, maxaciertos, numpartidas));
    }

    public Cursor getEntriesPerfiles () {
        String[] columns = new String[]{
                DbContract.DbEntry.COLUMN_NAME_FOTO,
                DbContract.DbEntry.COLUMN_NAME_ALIAS,
                DbContract.DbEntry.COLUMN_NAME_FECHAULTIMA,
                DbContract.DbEntry.COLUMN_NAME_MAXACIERTOS,
                DbContract.DbEntry.COLUMN_NAME_NUMPARTIDAS};
        return db.query(DbContract.DbEntry.TABLE_NAME_PERFILES, columns, null, null,
                null, null, null);
    }

    public void deleteAllPerfiles() {
        this.db.delete(DbContract.DbEntry.TABLE_NAME_PERFILES, null,null);
    }

    public void deletePerfilesRow(String alias){
        db.execSQL("DELETE FROM perfiles WHERE alias='" + alias + "'");
    }

    private ContentValues generateContentValuesPerfiles(String foto, String alias, String fechaultima, int maxaciertos, int numpartidas){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.DbEntry.COLUMN_NAME_FOTO, foto);
        contentValues.put(DbContract.DbEntry.COLUMN_NAME_ALIAS, alias);
        contentValues.put(DbContract.DbEntry.COLUMN_NAME_FECHAULTIMA, fechaultima);
        contentValues.put(DbContract.DbEntry.COLUMN_NAME_MAXACIERTOS, maxaciertos);
        contentValues.put(DbContract.DbEntry.COLUMN_NAME_NUMPARTIDAS, numpartidas);

        return contentValues;
    }

}
