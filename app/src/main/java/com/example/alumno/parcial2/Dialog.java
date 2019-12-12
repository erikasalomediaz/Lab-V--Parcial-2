package com.example.alumno.parcial2;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

/**
 * Created by alumno on 28/11/2019.
 */

public class Dialog extends DialogFragment {

public AlertDialog onCreateDialog(Bundle bundle){
AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
builder.setTitle("Error");
builder.setMessage("Debe ingresar un email y un password válido");
builder.setPositiveButton("Aceptar",null);
AlertDialog ad = builder.create();
return  ad;
}
}




