package com.example.app1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Formulario_Login extends AppCompatActivity {

    EditText txtContrasena, txtCorreo;
    private Button btnRegistrar;
    TextView contra;
    private Button btnIniciar;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_login);

        btnRegistrar = findViewById(R.id.botonRegistrar);
        btnIniciar = findViewById(R.id.botonIniciar);

        txtContrasena = findViewById(R.id.textFieldContrasena);
        txtCorreo = findViewById(R.id.textFieldCorreo);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFormulario_Sign_In();
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarUsuario();
                //openLoading_Screen();
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

    private void iniciarUsuario() {
        final String correo = txtCorreo.getText().toString().trim();
        String contrasena = txtContrasena.getText().toString().trim();
        final Intent intent = new Intent(this, Loading_Screen.class);

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(contrasena)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            int pos = correo.indexOf("@");
                            String user = correo.substring(0, pos);
                            Toast.makeText(Formulario_Login.this, "Bienvenido: " + txtCorreo.getText(), Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            //Intent intencion = new Intent(getApplication(), Loading_Screen.class);
                            //intencion.putExtra(Loading_Screen.user, user);
                            //startActivity(intencion);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Formulario_Login.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Formulario_Login.this, "Correo o Contraseña Incorrectos!", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }



}
