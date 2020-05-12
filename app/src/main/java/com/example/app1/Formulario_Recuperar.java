package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Formulario_Recuperar extends AppCompatActivity {

    private Button recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_recuperar);

        recuperar = findViewById(R.id.botonRecuperar);

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnviar_Codigo_2();
            }
        });
    }

    public void openEnviar_Codigo_2() {
        Intent intent = new Intent(this, Enviar_Codigo_2.class);
        startActivity(intent);
    }
}
