package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Formulario_Sign_In extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__sign__in);

        button1= (Button) findViewById(R.id.botonRegistrar);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnviar_Codigo_1();
            }
        });

        button2= (Button) findViewById(R.id.botonIniciar);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormulario_Login();
            }
        });
    }

    public void openEnviar_Codigo_1(){
        Intent intent = new Intent(this, Enviar_Codigo_1.class);
        startActivity(intent);
    }

    public void openFormulario_Login(){
        Intent intent = new Intent(this, Formulario_Login.class);
        startActivity(intent);
    }
}
