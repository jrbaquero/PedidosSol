package com.example.usuario.pedidos_01.recyclerView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.pedidos_01.R;

/**
 * Created by Next University
 */
public class RecyclerViewHolderPedidos extends RecyclerView.ViewHolder {
    TextView txtFEngrega, txtCliente,txtDescrRapida;
    TextView txtPrioridad,txtHora;

    CardView cardViewPedidos;


    public RecyclerViewHolderPedidos(View itemView) {
        super(itemView);

        cardViewPedidos = (CardView) itemView.findViewById(R.id.cardViewPedidos);
        txtFEngrega= (TextView)itemView.findViewById(R.id.txt_f_Entrega);
        txtCliente= (TextView)itemView.findViewById(R.id.txt_cliente);
        txtPrioridad = (TextView) itemView.findViewById(R.id.txt_prioridad);
        txtDescrRapida = (TextView) itemView.findViewById(R.id.txt_descr_rapida);
        txtHora = (TextView) itemView.findViewById(R.id.txt_hora);



    }
}
