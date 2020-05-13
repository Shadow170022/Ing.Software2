package com.example.app1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Formulario_Sign_In extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText txtNombre, txtApellidos, txtNumero, txtContrasena, txtCorreo;
    //Spinner txtLada;
    EditText txtLada;
    private Boolean registrado = false;
    private Button btnRegistrar;
    private Button btnIniciar;
    private DatabaseReference baseDeDatos;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

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
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        registrado = false;

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
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

    public void openThis() {
        Intent intent = new Intent(this, Formulario_Sign_In.class);
        startActivity(intent);
    }

    private void registrarUsuario() {

        String email = txtCorreo.getText().toString().trim();
        String password = txtContrasena.getText().toString().trim();
        final String nombre = txtNombre.getText().toString();
        final String apellidos = txtApellidos.getText().toString();
        final String lada = txtLada.getText().toString();
        //String lada = txtLada.getSelectedItem().toString();
        final String telefono = txtNumero.getText().toString();
        final String contrasena = txtContrasena.getText().toString();
        final String correo = txtCorreo.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (!TextUtils.isEmpty(nombre) || !TextUtils.isEmpty(apellidos) || !TextUtils.isEmpty(lada) || !TextUtils.isEmpty(telefono) || !TextUtils.isEmpty(contrasena) || !TextUtils.isEmpty(correo)) {

            progressDialog.setMessage("Realizando registro en linea...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {

                                String idUsuario = baseDeDatos.push().getKey();
                                Clases app1 = new Clases(idUsuario, nombre, apellidos, lada, telefono, contrasena, correo);
                                baseDeDatos.child("User").child(idUsuario).setValue(app1);
                                Toast.makeText(Formulario_Sign_In.this, "Se ha registrado el usuario con el email: " + txtCorreo.getText(), Toast.LENGTH_LONG).show();

                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(Formulario_Sign_In.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Formulario_Sign_In.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                                    openThis();
                                }
                            }
                            progressDialog.dismiss();
                        }
                    });
        } else {
            Toast.makeText(this, "NO DEBE HABER CAMPOS VACIOS", Toast.LENGTH_LONG).show();
        }

    }
}
