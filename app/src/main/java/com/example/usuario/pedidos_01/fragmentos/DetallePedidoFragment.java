package com.example.usuario.pedidos_01.fragmentos;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.usuario.pedidos_01.MainActivity;
import com.example.usuario.pedidos_01.R;
import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.baseDatos.Estructura;
import com.example.usuario.pedidos_01.dialogos.DialogoConfirmar;

import java.text.NumberFormat;


/**
 * Created by Usuario on 29/12/2017.
 */

public class DetallePedidoFragment extends Fragment {
    private static final String TAG = "Detalle";


    String fecha, clientes,detalle, descri_rapida,hora;
    Float precio,abono,saldo;

    TextView txtFecha, txtCliente,txtPrecio,txtAbono,txtSaldo,txtDetalle;
    TextView txtDescrRapida,txtHora;

    LayoutInflater inflater;

    NumberFormat format;
    BaseDatos basedatos;
    SQLiteDatabase sq;
    int Id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_pedido, container, false);
        Log.i("TAG", "onClick: Ingresa Fragment Detalle");

        Bundle datos = getActivity().getIntent().getExtras();
        Id = datos.getInt("ID");

        format = NumberFormat.getCurrencyInstance();

        txtFecha = (TextView)  view.findViewById(R.id.txt_fecha);
        txtCliente = (TextView) view.findViewById(R.id.txt_cliente);
        txtPrecio = (TextView) view.findViewById(R.id.txt_precio);
        txtAbono = (TextView) view.findViewById(R.id.txt_abono);
        txtSaldo = (TextView) view.findViewById(R.id.txt_saldo);
        txtDetalle = (TextView) view.findViewById(R.id.txt_descripcion);
        txtDescrRapida = (TextView) view.findViewById(R.id.txt_descr_rapida);
        txtHora = (TextView) view.findViewById(R.id.txt_hora);


        basedatos = new BaseDatos(getActivity());
        sq = basedatos.getWritableDatabase();
        Cursor c = sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_PEDIDOS+" WHERE "
                +Estructura.EstructuraBase._ID+" = ?  ;", new String[] {Integer.toString(Id)});


        if(c.moveToFirst()) {
            do {


                fecha = (c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_FECHA))));
                clientes = (c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_CLIENTE))));
                precio = (c.getFloat((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_PRECIO))));
                abono = (c.getFloat((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_ABONO))));
                saldo = (c.getFloat((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_SALDO))));
                detalle = (c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_DESCRIPCION))));
                descri_rapida = (c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_DESCR_RAPIDA))));
                hora = c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_HORA)));



            } while (c.moveToNext());
        }


        txtFecha.setText(fecha);
        txtCliente.setText(clientes);
        txtPrecio.setText(format.format(precio));
        txtAbono.setText(format.format(abono));
        txtSaldo.setText(format.format(saldo));
        txtDetalle.setText(detalle);
        txtDescrRapida.setText(descri_rapida);
        txtHora.setText(hora);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btn_Cancelar = view.findViewById(R.id.btn_cancelar);
        Button btn_Engregado = view.findViewById(R.id.btn_entregado);
        final ImageView imgEdit =(ImageView) view.findViewById(R.id.img_edit);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea el nuevo fragmento y la transacción.
                //se envia la informacion al fragment para poder editar
                Fragment fragment = new EditDetallePedidoFragment();
                Bundle dato = new Bundle();
                dato.putInt("ID",Id);
                dato.putString("cliente",txtCliente.getText().toString());
                dato.putString("fecha",txtFecha.getText().toString());
                dato.putString("precio",txtPrecio.getText().toString());
                dato.putString("abono",txtAbono.getText().toString());
                dato.putString("saldo",txtSaldo.getText().toString());
                dato.putString("detalle",txtDetalle.getText().toString());
                dato.putString("descRapida",txtDescrRapida.getText().toString());
                dato.putString("hora",txtHora.getText().toString());

                fragment.setArguments(dato);
                FragmentTransaction transaction = getFragmentManager().beginTransaction()
                .replace(R.id.content_segunda,fragment)
                .addToBackStack(null);
                  transaction.commit();


            }
        });
        btn_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent( getActivity() ,MainActivity.class);
                startActivity(intent);
                getActivity().finish();


            }
        });
        btn_Engregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle =new Bundle();
                bundle.putInt("ID",Id);
                DialogoConfirmar confirmar = new DialogoConfirmar();
                confirmar.setArguments(bundle);
                confirmar.show(getFragmentManager(),"Confirmar");


            }
        });

    }
}
