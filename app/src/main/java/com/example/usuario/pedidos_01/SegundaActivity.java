package com.example.usuario.pedidos_01;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.usuario.pedidos_01.fragmentos.DetallePedidoFragment;
import com.example.usuario.pedidos_01.fragmentos.DetalleVentasFragment;
import com.example.usuario.pedidos_01.fragmentos.Pendientes_Fragment;
import com.example.usuario.pedidos_01.fragmentos.VentasFragment;

public class SegundaActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment fragment = null;
    int Id;
    String nombreFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        //se cargar el nombre del fragmento a cargar

        Bundle datos = this.getIntent().getExtras();
        nombreFragment = datos.getString("NombreFragment");
        Id = datos.getInt("ID");



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });

        switch (nombreFragment) {
            case "Ventas":
                fragment = new VentasFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_segunda, fragment).commit();
                getSupportActionBar().setTitle(nombreFragment);
                break;

            case "Pedidos":
                Bundle bundle = new Bundle();
                bundle.putInt("ID",Id);
                fragment = new DetallePedidoFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_segunda, fragment).commit();
                getSupportActionBar().setTitle(nombreFragment);
                break;

            case "Pendientes":
                fragment = new Pendientes_Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_segunda, fragment).commit();
                getSupportActionBar().setTitle(nombreFragment);
                break;


        }





    }
    @Override
    public void onResume() {
        super.onResume();


        int colorPrimary = ContextCompat.getColor(getApplication(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplication(), R.color.colorPrimaryDark);

        Drawable bgColor = new ColorDrawable(colorPrimary);

        if (getSupportActionBar() != null)
            getSupportActionBar().setBackgroundDrawable(bgColor);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(colorPrimaryDark);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
