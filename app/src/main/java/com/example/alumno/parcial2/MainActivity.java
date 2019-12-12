package com.example.alumno.parcial2;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Handler.Callback {
    Button btnIn;
    EditText etEmail;
    EditText etClave;
    String email;
    String clave;
    Handler handler;
    SharedPreferences shared;
    CheckBox cbxGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etClave = (EditText)findViewById(R.id.etClave);
        cbxGuardar = (CheckBox) findViewById(R.id.cbxGuardar);
         btnIn = (Button)findViewById(R.id.btnIngresar);
        btnIn.setOnClickListener(this);
        shared = getSharedPreferences("MiShared", MODE_PRIVATE);

        //aca todo los datos del sharedPreferences
        email = shared.getString("email", null);
        clave = shared.getString("clave", null);

        if(!(email== null) && !(clave==null)){

           etEmail.setText(email);
            etClave.setText(clave);
            this.llamarHilo();


        }


    }

    @Override
    public void onClick(View v) {
        email = etEmail.getText().toString();
        clave = etClave.getText().toString();

        if(!(email== null) && !(clave==null)) {
            if(cbxGuardar.isChecked()) {
                SharedPreferences.Editor ed = shared.edit();
                ed.putString("email", email);
                ed.putString("clave", clave);
                ed.commit();
            }
            this.llamarHilo();
        }
    }


    public void llamarHilo(){

        Uri.Builder params = new Uri.Builder();
        params.appendQueryParameter("email",etEmail.getText().toString());
        params.appendQueryParameter("password",clave);
        handler = new Handler(this);
        MiHilo miHilo = new MiHilo(params, handler);
        miHilo.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

        if (msg.obj.equals("Ok")) {
            Intent i = new Intent(this, ConfirmActivity.class);
            i.putExtra("resultado", "Logueado Correctamente");
            startActivity(i);
        }else{
            Dialog dialog = new Dialog();
            dialog.show(getSupportFragmentManager(),"onCreate");
        }
        return false;
    }
}
