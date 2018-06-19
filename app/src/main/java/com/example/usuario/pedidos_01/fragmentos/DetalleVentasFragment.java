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
    TextView TxtMesSelec, TxtDetalle,TxtTotal;
    Float totalMes,totalAnio;
    String MesSelec,cliente,Detalle;
    int MesSelecDecimal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_ventas, container, false);

        Log.i("TAG", "\nonClick: Ingresa Detalle VENTAS");

        TxtDetalle = (TextView) view.findViewById(R.id.txt_detalle);
        TxtTotal =(TextView) view.findViewById(R.id.txt_total);

        basedatos = new BaseDatos(getActivity());
        sq = basedatos.getWritableDatabase();
        Mes =0;
        totalAnio=0f;

        Bundle bundle = getArguments();
        MesSelec = bundle.getString("Mes");
        MesSelecDecimal=bundle.getInt("MesSelec");

        TxtMesSelec = (TextView) view.findViewById(R.id.txt_mesSelec);


        TxtMesSelec.setText(MesSelec);
        crearTablaVentas(MesSelecDecimal);
         Log.i("TAG", "Total Mes:"+"AÑO  valor:"+totalAnio+ " mes:"+MesSelec+" "+6);
        Log.i("TAG", "************************************************");
        TxtDetalle.setText(Detalle);
        TxtTotal.setText("Total: "+Float.toString(totalAnio));
      return view;
    }



    private void crearTablaVentas(int mes) {
        Log.i("TAG", "************************************************");
        Log.i("TAG", "**********Ingresa Crear Tabla ventas **********************");
        Cursor c = sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_PEDIDOS+" WHERE "
                +Estructura.EstructuraBase.COLUMNA_ENREGADO+" = ?  AND "
                +Estructura.EstructuraBase.COLUMNA_MES+"=?;", new String[] {"1",Integer.toString(mes)});
        int tamano=0;
        totalMes=0f;
        totalAnio=0f;
        Detalle="";
        if(c.moveToFirst()) {
            do {

                totalMes =c.getFloat(c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_PRECIO));
                cliente = c.getString(c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_CLIENTE));
                totalAnio=totalMes+totalAnio;
                tamano++;
               Detalle = Integer.toString(tamano)+"\t"+cliente + "\t\t\t$"+Float.toString(totalMes)+"\n" +Detalle;
                Log.i("TAG", "\n"+tamano+" valor MES:"+totalMes+" valor Año:"+totalAnio+" Cliente "+cliente);

            } while (c.moveToNext());
        }



    }




}
