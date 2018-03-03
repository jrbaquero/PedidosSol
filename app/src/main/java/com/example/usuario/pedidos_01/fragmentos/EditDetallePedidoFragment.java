package com.example.usuario.pedidos_01.fragmentos;

import android.app.DatePickerDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.usuario.pedidos_01.R;
import com.example.usuario.pedidos_01.baseDatos.BaseDatos;
import com.example.usuario.pedidos_01.baseDatos.Estructura;
import com.example.usuario.pedidos_01.dialogos.DialogoDatePicker;
import com.example.usuario.pedidos_01.dialogos.DialogoTimePicker;
import com.example.usuario.pedidos_01.fragmentos.DetallePedidoFragment;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Usuario on 28/12/2017.
 */

public class EditDetallePedidoFragment extends Fragment {

    RecyclerView recyclerView;
    BaseDatos basedatos;
    SQLiteDatabase sq;

    TextInputLayout editCliente;
    TextInputLayout editPrecio;
    TextInputLayout editAbono;
    TextInputLayout editDescripcion;
    TextInputLayout editDescriRapida;
    TextInputLayout editSaldo;
    int Mes;
    int Anio;
    int entregado = 0;
    int Id;

    EditText editFecha, editPrecioEdit, editAbonoEdit, editHora;

    String fecha, cliente, precio, abono, descripcion, hora, descri_rapida, saldo;
    NumberFormat formatCurrency, formatNumero;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_pedido_edit, container, false);
        Log.i("TAG", "onClick: Ingresa Fragment Edicion");

        //recibo el id de la tabla seleccionada y los datos de la misma
        Bundle dato = getArguments();
        Id = dato.getInt("ID");
        cliente = dato.getString("cliente");
        fecha = dato.getString("fecha");
        precio = dato.getString("precio");
        abono = dato.getString("abono");
        saldo = dato.getString("saldo");
        descripcion = dato.getString("detalle");
        descri_rapida = dato.getString("descRapida");
        hora = dato.getString("hora");


        formatCurrency = NumberFormat.getCurrencyInstance();
        formatNumero = new DecimalFormat("##00");

        Button btnCambiar = (Button) view.findViewById(R.id.btn_cambiar);
        Button btnCancelar = (Button) view.findViewById(R.id.btn_cancelar);


        editFecha = (EditText) view.findViewById(R.id.edit_fecha);
        editHora = (EditText) view.findViewById(R.id.edit_hora);
        editPrecioEdit = (EditText) view.findViewById(R.id.edit_precioEdit);
        editAbonoEdit = (EditText) view.findViewById(R.id.edit_abonoEdit);


        editCliente = (TextInputLayout) view.findViewById(R.id.edit_cliente);
        editPrecio = (TextInputLayout) view.findViewById(R.id.edit_precio);
        editAbono = (TextInputLayout) view.findViewById(R.id.edit_abono);
        editDescripcion = (TextInputLayout) view.findViewById(R.id.edit_decripcion);
        editDescriRapida = (TextInputLayout) view.findViewById(R.id.edit_descri_rapida);
        editSaldo = (TextInputLayout) view.findViewById(R.id.edit_saldo);

        cargarDatosGuardados();


        editFecha.setOnClickListener(showDatePickerDialog);
        editHora.setOnClickListener(showTimePicker);

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCambiar();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui abri el framgmento anterior
                Fragment fragment = new DetallePedidoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction()
                        .replace(R.id.content_segunda, fragment)
                        .addToBackStack(null);
                // Commit a la transacción
                transaction.commit();


            }
        });

        return view;
    }

    private void cargarDatosGuardados() {

        editCliente.getEditText().setText(cliente);
        editFecha.setText(fecha);
        editPrecioEdit.setText(precio);
        editAbonoEdit.setText(abono);
        editSaldo.getEditText().setText(saldo);
        editDescripcion.getEditText().setText(descripcion);
        editDescriRapida.getEditText().setText(descri_rapida);
        editHora.setText(hora);
    }

    private void onClickCambiar() {

        fecha = null;
        if (editFecha != null && editFecha.getText() != null) {

            fecha = editFecha.getText().toString();
        }
        hora = null;
        if (editHora != null && editHora.getText() != null) {

            hora = editHora.getText().toString();
        }
        EditText editTextCliente = editCliente.getEditText();
        cliente = null;
        if (editTextCliente != null && editTextCliente.getText() != null) {

            cliente = editTextCliente.getText().toString();
        }
        EditText editTextPrecio = editPrecio.getEditText();
        precio = null;
        if (editTextPrecio != null && editTextPrecio.getText() != null) {

            precio = editTextPrecio.getText().toString();
        }
        EditText editTextAbono = editAbono.getEditText();
        abono = null;
        if (editTextAbono != null && editTextAbono.getText() != null) {

            abono = editTextAbono.getText().toString();
        }
        EditText editTextDescirpcion = editDescripcion.getEditText();
        descripcion = null;
        if (editTextDescirpcion != null && editTextDescirpcion.getText() != null) {

            descripcion = editTextDescirpcion.getText().toString();
        }
        EditText editTextDescrRapida = editDescriRapida.getEditText();
        descri_rapida = null;
        if (editTextDescrRapida != null && editTextDescrRapida.getText() != null) {

            descri_rapida = editTextDescrRapida.getText().toString();
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

        if (registro) {

            basedatos = new BaseDatos(getActivity());
            SQLiteDatabase sq = basedatos.getWritableDatabase();
            ContentValues content = new ContentValues();
            content.put(Estructura.EstructuraBase.COLUMNA_FECHA,editFecha.getText().toString());
            content.put(Estructura.EstructuraBase.COLUMNA_CLIENTE,editCliente.getEditText().getText().toString());
            content.put(Estructura.EstructuraBase.COLUMNA_PRECIO,editPrecio.getEditText().getText().toString());
            content.put(Estructura.EstructuraBase.COLUMNA_ABONO,editAbono.getEditText().getText().toString());
            content.put(Estructura.EstructuraBase.COLUMNA_SALDO,editSaldo.getEditText().getText().toString());
            content.put(Estructura.EstructuraBase.COLUMNA_DESCRIPCION,editDescripcion.getEditText().getText().toString());
            content.put(Estructura.EstructuraBase.COLUMNA_MES,Mes);
            content.put(Estructura.EstructuraBase.COLUMNA_ANIO,Anio);
            content.put(Estructura.EstructuraBase.COLUMNA_DESCR_RAPIDA,editDescriRapida.getEditText().getText().toString());
            content.put(Estructura.EstructuraBase.COLUMNA_HORA,hora);


            sq.update(Estructura.EstructuraBase.TABLE_PEDIDOS, content,
                    Estructura.EstructuraBase._ID+"=?",new String[]{Integer.toString(Id)});

            //aqui abri el framgmento anterior
            Fragment fragment = new DetallePedidoFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction()
                    .replace(R.id.content_segunda, fragment)
                    .addToBackStack(null);
            // Commit a la transacción
            transaction.commit();
            Toast.makeText(getActivity(),"Los datos cambiados satisfactoriamente",Toast.LENGTH_SHORT).show();

        }
    }

    View.OnClickListener showDatePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.edit_fecha:
                    DialogoDatePicker dialogoDatePicker = DialogoDatePicker.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            // +1 because january is zero
                            final String selectedDate = formatNumero.format(day) + "/" + formatNumero.format(month + 1) + "/" + year;
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


                            final String selectedHour = formatNumero.format(hourOfDay) + "H" + formatNumero.format(minutes);

                            editHora.setText(selectedHour);
                        }

                    });

                    dialogoTimePicker.show(getActivity().getFragmentManager(), "datePicker");
                    break;
            }

        }

    };


}

