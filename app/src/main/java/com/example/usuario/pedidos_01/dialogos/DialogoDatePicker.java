package com.example.usuario.pedidos_01.dialogos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Usuario on 28/12/2017.
 */

public class DialogoDatePicker extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static DialogoDatePicker newInstance(DatePickerDialog.OnDateSetListener listener) {
        DialogoDatePicker dialogo = new DialogoDatePicker();
        dialogo.setListener(listener);
        return dialogo;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }


}
