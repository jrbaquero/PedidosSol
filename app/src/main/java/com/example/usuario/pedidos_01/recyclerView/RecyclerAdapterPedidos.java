package com.example.usuario.pedidos_01.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usuario.pedidos_01.R;
import com.example.usuario.pedidos_01.SegundaActivity;
import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.baseDatos.Estructura;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Next University
 */
public class RecyclerAdapterPedidos extends RecyclerView.Adapter<RecyclerViewHolderPedidos> {


    ArrayList<Integer> arreglo_ID = new ArrayList<Integer>();
    ArrayList<String> arreglo_fEntrega = new ArrayList<String>();
    ArrayList<String> arreglo_prioridad = new ArrayList<String>();
    ArrayList<String> arreglo_clientes = new ArrayList<String>();
   ArrayList<String> arreglo_descr_rapida = new ArrayList<String>();
    ArrayList<String> arreglo_hora = new ArrayList<String>();



    BaseDatos basedatos;
    Context context;
    LayoutInflater inflater;
    RecyclerViewHolderPedidos vh;
    NumberFormat format;

     public RecyclerAdapterPedidos(Context context){
        this.context=context;
        inflater= LayoutInflater.from(context);
         basedatos = new BaseDatos(context);
         SQLiteDatabase sq = basedatos.getWritableDatabase();
         //oredena la base en funcion de la fecha de forma acendente
         Cursor c = sq.rawQuery("SELECT * FROM "+ Estructura.EstructuraBase.TABLE_PEDIDOS
                 +" WHERE "+Estructura.EstructuraBase.COLUMNA_ENREGADO+"= ?  ORDER BY "
                 +Estructura.EstructuraBase.COLUMNA_FECHA+" ASC;", new String[] {"0"});
       int a=1;


         if(c.moveToFirst()) {
             do {
                 arreglo_prioridad.add(""+a);
                 arreglo_ID.add(c.getInt((c.getColumnIndex(Estructura.EstructuraBase._ID))));
                 arreglo_fEntrega.add(c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_FECHA))));
                 arreglo_clientes.add(c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_CLIENTE))));
                 arreglo_descr_rapida.add(c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_DESCR_RAPIDA))));
                 arreglo_hora.add(c.getString((c.getColumnIndex(Estructura.EstructuraBase.COLUMNA_HORA))));
                 a++;
             } while (c.moveToNext());
         }

    }

    @Override
    public RecyclerViewHolderPedidos onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.template_pedidos1,parent,false);
        RecyclerViewHolderPedidos view1= new RecyclerViewHolderPedidos(v);
        format = NumberFormat.getCurrencyInstance();

        return view1;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderPedidos holder, int position) {

        holder.txtPrioridad.setText(arreglo_prioridad.get(position));
        holder.txtFEngrega.setText(arreglo_fEntrega.get(position));
        holder.txtCliente.setText(arreglo_clientes.get(position));
        holder.txtDescrRapida.setText(arreglo_descr_rapida.get(position));
        holder.txtHora.setText(arreglo_hora.get(position));

        holder.cardViewPedidos.setOnClickListener(onClickListAbreActivity);
        holder.cardViewPedidos.setTag(holder);

    }




    View.OnClickListener onClickListAbreActivity= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vh=(RecyclerViewHolderPedidos)v.getTag();

            Bundle enviaId = new Bundle();
            enviaId.putInt("ID", arreglo_ID.get(vh.getPosition()));

            final Intent intent;
            intent =  new Intent(context, SegundaActivity.class);
            intent.putExtra("ID",arreglo_ID.get(vh.getPosition()));
            intent.putExtra("NombreFragment","Pedidos");
            context.startActivity(intent);



        }
    };





    @Override
    public int getItemCount() {
        return arreglo_fEntrega.size();
    }
}
