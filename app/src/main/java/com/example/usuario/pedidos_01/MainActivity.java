package com.example.usuario.pedidos_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.dialogos.DialogoConfirmar;
import com.example.usuario.pedidos_01.dialogos.DialogoRegistroPedido;
import com.example.usuario.pedidos_01.recyclerView.RecyclerAdapterPedidos;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;

    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RecyclerAdapterPedidos adapter= new RecyclerAdapterPedidos(getApplicationContext());
        recyclerView= (RecyclerView)findViewById(R.id.RecycleViewPedidos);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // aqui se hace que sea el grid de 2
       //recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
       recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickRegistroPedido();




            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exportar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
        switch(id){
            case R.id.nav_ventas:

                intent.putExtra("NombreFragment","Ventas");
                startActivity(intent);

                break;

            case R.id.nav_pendientes:

                intent.putExtra("NombreFragment","Pendientes");
                startActivity(intent);

                break;


        }
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }
    private void OnClickRegistroPedido() {
        DialogoRegistroPedido dialogo = new DialogoRegistroPedido();
        dialogo.show(getFragmentManager(),"MainActivy");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
