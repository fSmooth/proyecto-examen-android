package com.fsmooth.proyectoexamen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Sentencias SQL para crear las tablas Trabajadores y Formacion
    String sqlTrabajadores = "CREATE TABLE Trabajadores (id_trab INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "nombre TEXT, apellidos TEXT, edad INTEGER)";
    String sqlFormacion = "CREATE TABLE Formacion (id_form INTEGER PRIMARY KEY NOT NULL, " +
            "id_trab FOREIGN KEY NOT NULL, " +
            "nombre_curso TEXT, duracion INTEGER)";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlTrabajadores);
        db.execSQL(sqlFormacion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la versión anterior de las tablas
        db.execSQL("DROP TABLE IF EXISTS Trabajadores");
        db.execSQL("DROP TABLE IF EXISTS Formacion");

        // Se crea una nueva versión de las tablas
        db.execSQL(sqlTrabajadores);
        db.execSQL(sqlFormacion);
    }
}
