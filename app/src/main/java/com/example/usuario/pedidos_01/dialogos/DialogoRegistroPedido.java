package com.example.usuario.pedidos_01.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentValues;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.usuario.pedidos_01.R;
import com.example.usuario.pedidos_01.recyclerView.RecyclerAdapterPedidos;
import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.baseDatos.Estructura;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Usuario on 28/12/2017.
 */

public class DialogoRegistroPedido extends DialogFragment {

    RecyclerView recyclerView;
    BaseDatos baseDatos;
    SQLiteDatabase sq;

    TextInputLayout editCliente;
    TextInputLayout editPrecio;
    TextInputLayout editAbono;
    TextInputLayout editDescripcion;
    TextInputLayout editDescriRapida;
    int Mes;
    int Anio;
    int entregado = 0;
    TextView txtSaldo;

    EditText editFecha,editPrecioEdit,editAbonoEdit, editHora;

    String fecha,cliente, precio, abono,descripcion, hora,descri_rapida;
    float saldo;
    NumberFormat formatCurrency,formatNumero;




    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogo = inflater.inflate(R.layout.dialogo_registro_pedido, null);

        builder.setTitle(getResources().getString(R.string.str_registroPedido));
        builder.setView(dialogo);


        saldo=0;

        formatCurrency = NumberFormat.getCurrencyInstance();
        formatNumero = new DecimalFormat("##00");

        Button btnRegistar = (Button) dialogo.findViewById(R.id.btn_registrar);
        Button btnCancelar = (Button) dialogo.findViewById(R.id.btn_cancelar);



        editFecha = (EditText) dialogo.findViewById(R.id.edit_fecha);
        editHora = (EditText) dialogo.findViewById(R.id.edit_hora);
        editPrecioEdit =(EditText) dialogo.findViewById(R.id.edit_precioEdit);
        editAbonoEdit=(EditText) dialogo.findViewById(R.id.edit_abonoEdit);


        editCliente = (TextInputLayout) dialogo.findViewById(R.id.edit_cliente);
        editPrecio =(TextInputLayout) dialogo.findViewById(R.id.edit_precio);
        editAbono = (TextInputLayout) dialogo.findViewById(R.id.edit_abono);
        editDescripcion = (TextInputLayout) dialogo.findViewById(R.id.edit_decripcion);
        editDescriRapida = (TextInputLayout) dialogo.findViewById(R.id.edit_descri_rapida);

        txtSaldo = (TextView) dialogo.findViewById(R.id.txt_saldo);

        editFecha.setOnClickListener(showDatePickerDialog);

        editHora.setOnClickListener(showTimePicker);

        editAbonoEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length() != 0)
                {
                    EditText editTextPrecio = editPrecio.getEditText();
                    precio = null;
                    if (editTextPrecio != null && editTextPrecio.getText() != null) {

                        precio =editTextPrecio.getText().toString();
                    }
                    if ("".equals(precio)) {

                        precio ="0";
                    }

                    abono = editAbonoEdit.getText().toString();
                    saldo =  Float.parseFloat(precio)- Float.parseFloat(abono);
                    txtSaldo.setText("Saldo: "+formatCurrency.format(saldo));
                    //    txtSaldo.setText(editPrecioEdit.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editPrecioEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

                @Override
                public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if(s.length() != 0)
                {
                    EditText editTextAbono = editAbono.getEditText();
                    abono = null;
                    if (editTextAbono != null && editTextAbono.getText() != null) {

                        abono =editTextAbono.getText().toString();
                    }
                    if ("".equals(abono)) {

                        abono="0";
                    }


                    precio = editPrecioEdit.getText().toString();
                    saldo =  Float.parseFloat(precio)- Float.parseFloat(abono);
                    txtSaldo.setText("Saldo: "+formatCurrency.format(saldo));
                //    txtSaldo.setText(editPrecioEdit.getText().toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRegistrar();
            }
        });
        return builder.create();
    }

    private void onClickRegistrar() {

        fecha = null;
        if (editFecha != null && editFecha.getText() != null) {

            fecha =editFecha.getText().toString();
        }
        hora = null;
        if (editHora != null && editHora.getText() != null) {

            hora =editHora.getText().toString();
        }
        EditText editTextCliente = editCliente.getEditText();
        cliente = null;
        if (editTextCliente != null && editTextCliente.getText() != null) {

            cliente =editTextCliente.getText().toString();
        }
        EditText editTextPrecio = editPrecio.getEditText();
        precio = null;
        if (editTextPrecio != null && editTextPrecio.getText() != null) {

            precio=editTextPrecio.getText().toString();
        }
        EditText editTextAbono = editAbono.getEditText();
        abono = null;
        if (editTextAbono != null && editTextAbono.getText() != null) {

            abono =editTextAbono.getText().toString();
        }
        EditText editTextDescirpcion = editDescripcion.getEditText();
        descripcion = null;
        if (editTextDescirpcion != null && editTextDescirpcion.getText() != null) {

            descripcion =editTextDescirpcion.getText().toString();
        }
        EditText editTextDescrRapida = editDescriRapida.getEditText();
        descri_rapida = null;
        if (editTextDescrRapida != null && editTextDescrRapida.getText() != null) {

            descri_rapida =editTextDescrRapida.getText().toString();
        }


        boolean registro = true;
        if ("".equals(fecha)) {
            registro = false;
            editFecha.setError(getResources().getString(R.string.str_error_fecha));
        }
        if ("".equals(hora)) {
            registro = false;
            editHora.setError(getResources().getString(R.string.str_error_hora));
        }

        if ("".equals(cliente)) {
            registro = false;
            editCliente.setError(getResources().getString(R.string.str_error_cliente));
        }
        if ("".equals(precio)) {
            registro = false;
            editPrecio.setError(getResources().getString(R.string.str_error_precio));
        }
        if ("".equals(abono)) {
            registro = false;
            editAbono.setError(getResources().getString(R.string.str_error_abono));
        }

        if(registro) {

            baseDatos = new BaseDatos(getActivity());
            SQLiteDatabase sq = baseDatos.getWritableDatabase();
            ContentValues content = new ContentValues();
            content.put(Estructura.EstructuraBase.COLUMNA_FECHA, fecha);
            content.put(Estructura.EstructuraBase.COLUMNA_CLIENTE, cliente);
            content.put(Estructura.EstructuraBase.COLUMNA_PRECIO, precio);
            content.put(Estructura.EstructuraBase.COLUMNA_ABONO, abono);
            content.put(Estructura.EstructuraBase.COLUMNA_SALDO, saldo);
            content.put(Estructura.EstructuraBase.COLUMNA_DESCRIPCION, descripcion);
            content.put(Estructura.EstructuraBase.COLUMNA_ENREGADO, entregado);
            content.put(Estructura.EstructuraBase.COLUMNA_MES, Mes);
            content.put(Estructura.EstructuraBase.COLUMNA_ANIO, Anio);
            content.put(Estructura.EstructuraBase.COLUMNA_DESCR_RAPIDA, descri_rapida);
            content.put(Estructura.EstructuraBase.COLUMNA_HORA, hora);

            sq.insert(Estructura.EstructuraBase.TABLE_PEDIDOS, null, content);
            Toast.makeText(getActivity(),"Pedido Ingresado Correctamente ",Toast.LENGTH_SHORT).show();
            dismiss();
          //    sq.execSQL("DELETE FROM lista_pedidos");

            Activity activity = getActivity();
           RecyclerAdapterPedidos adapter= new RecyclerAdapterPedidos(getActivity());
            recyclerView= (RecyclerView) activity.findViewById(R.id.RecycleViewPedidos);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            // aqui se hace que sea el grid de 2
          // recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
     //       recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
    }

    View.OnClickListener showDatePickerDialog =new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.edit_fecha:
                    DialogoDatePicker dialogoDatePicker = DialogoDatePicker.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            // +1 because january is zero
                            final String selectedDate = formatNumero.format(day)  + "/" + formatNumero.format(month + 1) + "/" + year;
                            Mes = month;
                            Anio = year;
                            editFecha.setText(selectedDate);
                        }
                    });

                    dialogoDatePicker.show(getActivity().getFragmentManager(), "datePicker");
                    break;
            }

        }



    };

   View.OnClickListener showTimePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.edit_hora:
                    DialogoTimePicker dialogoTimePicker = DialogoTimePicker.newInstance(new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {


                            final String selectedHour = formatNumero.format(hourOfDay) + "H" + formatNumero.format(minutes) ;

                            editHora.setText( selectedHour);
                        }

                       });

                    dialogoTimePicker.show(getActivity().getFragmentManager(), "datePicker");
                    break;
            }

        }

    };



    }

