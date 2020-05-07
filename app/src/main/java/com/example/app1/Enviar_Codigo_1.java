package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Enviar_Codigo_1 extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar__codigo_1);
        button1= (Button) findViewById(R.id.botonValidar);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoading_Screen();
            }
        });
    }

    public void openLoading_Screen(){
        Intent intent = new Intent(this, Loading_Screen.class);
        startActivity(intent);
    }
}
