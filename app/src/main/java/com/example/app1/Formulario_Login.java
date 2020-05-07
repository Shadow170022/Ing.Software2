package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Formulario_Login extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_login);
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
                openLogin();
            }
        });
    }

    public void openFormulario_Sign_In(){
        Intent intent = new Intent(this, Formulario_Sign_In.class);
        startActivity(intent);
    }

    public void openLogin(){
        Intent intent = new Intent(this, Loading_Screen.class);
        startActivity(intent);
    }
}
