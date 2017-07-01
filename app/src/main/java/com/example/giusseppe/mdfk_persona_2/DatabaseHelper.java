package com.example.giusseppe.mdfk_persona_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giusseppe on 30/06/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    String tabla = "CREATE TABLE TB_PERSONAS(ID INTEGER PRIMARY KEY, NOMBRE TEXT, APELLIDO TEXT)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE TB_PERSONAS");
        sqLiteDatabase.execSQL(tabla);
    }
}
