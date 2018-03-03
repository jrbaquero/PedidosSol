package com.example.usuario.pedidos_01.baseDatos;

import android.provider.BaseColumns;

/**
 * Created by Next University
 */
public  class Estructura {

    public Estructura(){


    }

    public static abstract class EstructuraBase implements BaseColumns
    {
        public static final String TABLE_PEDIDOS = "lista_pedidos";
        public static final String COLUMNA_FECHA = "fecha";
        public static final String COLUMNA_CLIENTE = "cliente";
        public static final String COLUMNA_PRECIO = "precio";
        public static final String COLUMNA_ABONO = "abono";
        public static final String COLUMNA_SALDO = "saldo";
        public static final String COLUMNA_DESCRIPCION = "descripcion";
        public static final String COLUMNA_ENREGADO = "entregado";
        public static final String COLUMNA_MES = "mes";
        public static final String COLUMNA_ANIO = "anio";
        public static final String COLUMNA_DESCR_RAPIDA ="descr_rapida";
        public static final String COLUMNA_HORA ="hora";



        public static final String TABLE_PENDIENTES = "lista_pendientes";
        public static final String COLUMNA_PENDIENTES = "pendientes";





          }
}
