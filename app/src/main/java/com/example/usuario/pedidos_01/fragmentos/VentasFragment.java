package com.example.usuario.pedidos_01.fragmentos;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.usuario.pedidos_01.R;
import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.baseDatos.Estructura;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Usuario on 30/12/2017.
 */

public class VentasFragment extends Fragment {


    ArrayList<Float> arreglo_TotalMes = new ArrayList<Float>();
    NumberFormat format;
    BaseDatos basedatos;
    SQLiteDatabase sq;
    TextView txtEnero,txtFebrero,txtMarzo,txtAbril,txtMayo,txtJunio,txtTotal,txtEneroMes;
    TextView txtJulio,txtAgosto,txtSeptiembre,txtOctubre,txtNoviembre,txtDiciembre;
    int Mes;
    Float totalMes,totalAnio;
    String cliente;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_ventas, container, false);
        Log.i("TAG", "onClick: Ingresa VENTAS");

        format = NumberFormat.getCurrencyInstance();
        txtEnero = (TextView) view.findViewById(R.id.txt_enero);
        txtFebrero = (TextView) view.findViewById(R.id.txt_febrero);
        txtMarzo = (TextView) view.findViewById(R.id.txt_marzo);
        txtAbril = (TextView) view.findViewById(R.id.txt_abril);
        txtMayo = (TextView) view.findViewById(R.id.txt_mayo);
        txtJunio = (TextView) view.findViewById(R.id.txt_junio);
        txtJulio = (TextView) view.findViewById(R.id.txt_julio);
        txtAgosto = (TextView) view.findViewById(R.id.txt_agosto);
        txtSeptiembre = (TextView) view.findViewById(R.id.txt_septiembre);
        txtOctubre = (TextView) view.findViewById(R.id.txt_octubre);
        txtNoviembre = (TextView) view.findViewById(R.id.txt_noviembre);
        txtDiciembre = (TextView) view.findViewById(R.id.txt_diciembre);
        txtTotal = (TextView) view.findViewById(R.id.txt_total);

        txtEneroMes = (TextView) view.findViewById(R.id.txt_enero_mes);

        basedatos = new BaseDatos(getActivity());
        sq = basedatos.getWritableDatabase();
        Mes =0;
        totalAnio=0f;
        do {
            crearTablaVentas(Mes);
            escribeValoresLayout(Mes);

            Mes++;
        }while (Mes!=12);
        if(Mes==12){
            txtTotal.setText(format.format(totalAnio));
            arreglo_TotalMes=null;
        }

        txtEneroMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                bundle.putString("Mes","Enero");
//                fragment.setArguments(bundle);
                fragment = new DetalleVentasFragment();
                getFragmentManager().beginTransaction().replace(R.id.content_segunda, fragment).commit();

            }
        });


      return view;
    }

    private void escribeValoresLayout(int i) {
        switch (i){
            case  0:
                txtEnero.setText(format.format(totalMes));
                break;
            case  1:
                txtFebrero.setText(format.format(totalMes));
                break;
            case  2:
                txtMarzo.setText(format.format(totalMes));
                break;
            case  3:
                txtAbril.setText(format.format(totalMes));
                break;
            case  4:
                txtMayo.setText(format.format(totalMes));
                break;
            case  5:
                txtJunio.setText(format.format(totalMes));
                break;
            case  6:
                txtJulio.setText(format.format(totalMes));
                break;
            case  7:
                txtAgosto.setText(format.format(totalMes));
                break;
            case  8:
                txtSeptiembre.setText(format.format(totalMes));
                break;
            case  9:
                txtOctubre.setText(format.format(totalMes));
                break;
            case  10:
                txtNoviembre.setText(format.format(totalMes));
                break;
            case  11:
                txtNoviembre.setText(format.format(totalMes));
                break;
        }
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
