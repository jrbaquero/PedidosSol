package com.example.usuario.pedidos_01.fragmentos;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.pedidos_01.R;
import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.baseDatos.Estructura;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Usuario on 30/12/2017.
 */

public class DetalleVentasFragment extends Fragment {


    ArrayList<Float> arreglo_TotalMes = new ArrayList<Float>();
    NumberFormat format;
    BaseDatos basedatos;
    SQLiteDatabase sq;

    int Mes;
    TextView TxtMesSelec;
    Float totalMes,totalAnio;
    String MesSelec,cliente;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_ventas, container, false);
        Log.i("TAG", "onClick: Ingresa Detalle VENTAS");


        basedatos = new BaseDatos(getActivity());
        sq = basedatos.getWritableDatabase();
        Mes =0;
        totalAnio=0f;

        Bundle bundle = getArguments();
//        MesSelec = bundle.getString("Mes");

        TxtMesSelec = (TextView) view.findViewById(R.id.txt_mesSelec);

        TxtMesSelec.setText("Enero");

      return view;
    }



    private void crearTablaVentas(int mes) {

        Cursor c = sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_PEDIDOS+" WHERE "
                +Estructura.EstructuraBase.COLUMNA_ENREGADO+" = ?  AND "
                +Estructura.EstructuraBase.COLUMNA_MES+"=?;", new String[] {"1",Integer.toString(mes)});
        int tamano=0;
        totalMes=0f;

        if(c.moveToFirst()) {
            do {

                totalMes =c.getFloat(c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_PRECIO))+totalMes;
                Log.i("TAG", "MES "+mes+" valor:"+totalMes+" Cliente "+cliente);
                tamano++;
            } while (c.moveToNext());
        }
            totalAnio=totalMes+totalAnio;
        Log.i("TAG", "AÑO  valor:"+totalAnio);

    }




}
