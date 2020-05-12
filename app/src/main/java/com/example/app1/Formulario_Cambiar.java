package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Formulario_Cambiar extends AppCompatActivity {

    private Button cambiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cambiar);

        cambiar = findViewById(R.id.botonCambiar);

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormulario_Login();
            }
        });
    }

    public void openFormulario_Login() {
        Intent intent = new Intent(this, Formulario_Login.class);
        startActivity(intent);
    }
}
