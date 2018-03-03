package com.example.usuario.pedidos_01.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.Toast;

import com.example.usuario.pedidos_01.MainActivity;
import com.example.usuario.pedidos_01.R;
import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.baseDatos.Estructura;

/**
 * Created by Usuario on 04/01/2018.
 */

public class DialogoConfirmar extends android.support.v4.app.DialogFragment{

    SQLiteDatabase sq;
    BaseDatos basedatos;
    int Id;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Corfirmar Entrega");
        basedatos = new BaseDatos(getActivity());
        sq = basedatos.getWritableDatabase();

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = getArguments();
                Id= bundle.getInt("ID");
                ContentValues content = new ContentValues();
                content.put(Estructura.EstructuraBase.COLUMNA_ENREGADO,1);
                sq.update(Estructura.EstructuraBase.TABLE_PEDIDOS, content,
                        Estructura.EstructuraBase._ID+"=?",new String[]{Integer.toString(Id)});


                Toast.makeText(getActivity(),"Pedido Entregado: "+Id,Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent( getActivity() ,MainActivity.class);
                dismiss();
                startActivity(intent);


            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }


}
