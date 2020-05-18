package com.example.app1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

public class Map_Screen extends AppCompatActivity {


    public static String usuario = "nombre";
    TextView textUser;
    EditText origen, destino;
    Button btnSolicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__screen);

        textUser = findViewById(R.id.txtUsuario);
        origen = findViewById(R.id.textFieldOrigen);
        destino = findViewById(R.id.textFieldDestino);
        btnSolicitar = findViewById(R.id.botonSolicitar);


        //the color is a direct color int and not a color resource


        destino.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0 && origen.getText().toString().length() > 0) {
                    btnSolicitar.setEnabled(true);
                    Drawable buttonDrawable = btnSolicitar.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, Color.parseColor("#3E5286"));
                    btnSolicitar.setBackground(buttonDrawable);
                } else {
                    btnSolicitar.setEnabled(false);
                }
            }
        });

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapa();
            }
        });

        String usuario = getIntent().getStringExtra("nombre");
        textUser.setText(String.format("Hola %s :3", usuario));

    }

    public void openMapa() {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }
}
