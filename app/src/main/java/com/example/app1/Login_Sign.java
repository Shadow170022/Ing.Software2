package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Módulo para inicio de sesión 
//Formulario  conectado con la Base de Datos
public class Login_Sign extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__sign);

        button1= (Button) findViewById(R.id.botonRegistrar);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormulario_Sign_In();
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

    public void openFormulario_Sign_In(){
        Intent intent = new Intent(this, Formulario_Sign_In.class);
        startActivity(intent);
    }

    public void openFormulario_Login(){
        Intent intent = new Intent(this, Formulario_Login.class);
        startActivity(intent);
    }
}
