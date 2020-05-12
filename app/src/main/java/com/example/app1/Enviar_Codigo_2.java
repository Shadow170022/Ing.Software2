package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Enviar_Codigo_2 extends AppCompatActivity {

    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_codigo_2);

        button2 = findViewById(R.id.botonValidar);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormulario_Cambiar();
            }
        });
    }

    public void openFormulario_Cambiar() {
        Intent intent = new Intent(this, Formulario_Cambiar.class);
        startActivity(intent);
    }
}
