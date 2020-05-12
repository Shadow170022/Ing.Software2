package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Formulario_Login extends AppCompatActivity {

    private Button button1;
    private Button button2;
    TextView contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_login);
        button1 = findViewById(R.id.botonRegistrar);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormulario_Sign_In();
            }
        });

        button2 = findViewById(R.id.botonIniciar);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoading_Screen();
            }
        });

        contra = findViewById(R.id.preguntaContrasena);

        contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormulario_recuperar();
            }
        });
    }

    public void openFormulario_Sign_In(){
        Intent intent = new Intent(this, Formulario_Sign_In.class);
        startActivity(intent);
    }

    public void openLoading_Screen(){
        Intent intent = new Intent(this, Loading_Screen.class);
        startActivity(intent);
    }

    public void openFormulario_recuperar() {
        Intent intent = new Intent(this, Formulario_Recuperar.class);
        startActivity(intent);
    }
}
