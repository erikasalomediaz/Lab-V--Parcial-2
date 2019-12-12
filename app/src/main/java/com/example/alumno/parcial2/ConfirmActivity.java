package com.example.alumno.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        TextView tvMensaje = (TextView) findViewById(R.id.tvResultado);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String aux = bundle.getString("resultado");
        tvMensaje.setText(aux);
    }
}
