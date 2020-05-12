package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Formulario_Sign_In extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText txtNombre, txtApellidos, txtNumero, txtContrasena, txtCorreo;
    //Spinner txtLada;
    EditText txtLada;
    private Button btnRegistrar;
    private Button btnIniciar;
    private DatabaseReference baseDeDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario__sign__in);

        btnRegistrar = findViewById(R.id.botonRegistrar);
        btnIniciar = findViewById(R.id.botonIniciar);

        txtNombre = findViewById(R.id.textFieldNombre);
        txtApellidos = findViewById(R.id.textFieldApellidos);
        txtLada = findViewById(R.id.textFieldTelefonoLada);
        txtNumero = findViewById(R.id.textFieldTelefono);
        txtContrasena = findViewById(R.id.textFieldContrasena);
        txtCorreo = findViewById(R.id.textFieldCorreo);

        baseDeDatos = FirebaseDatabase.getInstance().getReference("Clases");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarClase();
                //openEnviar_Codigo_1();
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
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

    public void registrarClase() {
        String nombre = txtNombre.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String lada = txtLada.getText().toString();
        //String lada = txtLada.getSelectedItem().toString();
        String telefono = txtNumero.getText().toString();
        String contrasena = txtContrasena.getText().toString();
        String correo = txtCorreo.getText().toString();

        if (!TextUtils.isEmpty(nombre) || !TextUtils.isEmpty(apellidos) || !TextUtils.isEmpty(lada) || !TextUtils.isEmpty(telefono) || !TextUtils.isEmpty(contrasena) || !TextUtils.isEmpty(correo)) {
            String idUsuario = baseDeDatos.push().getKey();
            Clases app1 = new Clases(idUsuario, nombre, apellidos, lada, telefono, contrasena, correo);
            baseDeDatos.child("User").child(idUsuario).setValue(app1);

            Toast.makeText(this, "Usuario en proceso de verificacion", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "NO DEBE HABER CAMPOS VACIOS", Toast.LENGTH_LONG).show();
        }
    }
}
