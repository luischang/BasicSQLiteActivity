package com.example.lchang.basicsqliteactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre de la tabla
    public static final String TABLE_NAME = "PAISES";

    // Columnas de la tabla
    public static final String _ID = "_id";
    public static final String PAIS = "pais";
    public static final String MONEDA = "moneda";

    // Nombre de la base de datos
    static final String DB_NAME = "MOVILES.DB";

    // Versión de la base de datos(importante)
    static final int DB_VERSION = 2;

    // Script para la creación de la tabla
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PAIS + " TEXT NOT NULL, " + MONEDA + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}