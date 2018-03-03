package com.example.usuario.pedidos_01.baseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Next University
 */
public class BaseDatos extends SQLiteOpenHelper {
    //Declaramos textos auxiliares
    private static final String tipo = " TEXT";
    private static final String Coma = ",";

    //sentencia de creacion de base de datos (implementendo la estructura)
    private static final String Sentencia =
            "CREATE TABLE " + Estructura.EstructuraBase.TABLE_PEDIDOS + " ("
                    + Estructura.EstructuraBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Estructura.EstructuraBase.COLUMNA_FECHA+tipo+Coma
                    + Estructura.EstructuraBase.COLUMNA_CLIENTE+tipo+Coma
                    + Estructura.EstructuraBase.COLUMNA_PRECIO+" REAL"+Coma
                    + Estructura.EstructuraBase.COLUMNA_ABONO+" REAL"+Coma
                    + Estructura.EstructuraBase.COLUMNA_SALDO+" REAL"+Coma
                    + Estructura.EstructuraBase.COLUMNA_DESCRIPCION+tipo+Coma
                    + Estructura.EstructuraBase.COLUMNA_ENREGADO+" INTEGER"+Coma
                    + Estructura.EstructuraBase.COLUMNA_MES+" INTEGER"+Coma
                    + Estructura.EstructuraBase.COLUMNA_ANIO+" INTEGER"+Coma
                    + Estructura.EstructuraBase.COLUMNA_DESCR_RAPIDA+tipo+Coma
                    + Estructura.EstructuraBase.COLUMNA_HORA +tipo+ " );";


    private static final String Sentencia2 =
            "CREATE TABLE " + Estructura.EstructuraBase.TABLE_PENDIENTES + " ("
                    + Estructura.EstructuraBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Estructura.EstructuraBase.COLUMNA_PENDIENTES +tipo+ " );";



    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pedido_10.sqlite";
   //si la tabla existe
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Estructura.EstructuraBase.TABLE_PEDIDOS;
    private static final String SQL_DELETE_ENTRIES1 = "DROP TABLE IF EXISTS " + Estructura.EstructuraBase.TABLE_PENDIENTES;


    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(Sentencia);
        db.execSQL(Sentencia2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES1);
        onCreate(db);
    }
}
