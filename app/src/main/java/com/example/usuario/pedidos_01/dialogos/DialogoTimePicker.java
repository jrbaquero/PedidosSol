package com.example.usuario.pedidos_01.dialogos;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Usuario on 28/12/2017.
 */

public class DialogoTimePicker extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener listener;

    public static DialogoTimePicker newInstance(TimePickerDialog.OnTimeSetListener listener) {
        DialogoTimePicker dialogo = new DialogoTimePicker();
        dialogo.setListener(listener);
        return dialogo;
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker


        final Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        boolean formatTime = true;

        // Create a new instance of DatePickerDialog and return it
        return new TimePickerDialog(getActivity(), listener, hourOfDay, minute, formatTime);
    }


}
