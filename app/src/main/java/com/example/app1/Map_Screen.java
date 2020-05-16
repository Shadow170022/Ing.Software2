package com.example.app1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Map_Screen extends AppCompatActivity {


    public static String usuario = "nombre";
    TextView textUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__screen);

        textUser = findViewById(R.id.txtUsuario);
        String usuario = getIntent().getStringExtra("nombre");
        textUser.setText(String.format("Hola %s :3", usuario));


    }
}
