package com.example.usuario.pedidos_01.fragmentos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.pedidos_01.R;

/**
 * Created by Usuario on 30/12/2017.
 */

public class Pendientes_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pendientes, container, false);
        Log.i("TAG", "onClick: Ingresa Fragment Detalle");

        return view;
    }
}
