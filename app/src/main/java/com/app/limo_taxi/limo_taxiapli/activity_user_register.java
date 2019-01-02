package com.app.limo_taxi.limo_taxiapli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class activity_user_register extends AppCompatActivity {


    private EditText nombre;
    private EditText apellido;
    private EditText cedula;
    private EditText telefono;
    private EditText correo;
    private EditText password;
    private Button registar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        nombre= (EditText)findViewById(R.id.txtNombreUsuario);
        apellido=(EditText)findViewById(R.id.txtApellidoUsuario);
        cedula=(EditText)findViewById(R.id.txtCedula);
        telefono=(EditText)findViewById(R.id.txtTelefono);
        correo=(EditText)findViewById(R.id.txtCorreoUsuario);
        password=(EditText)findViewById(R.id.txtContraseniaUsuario);
        registar=(Button)findViewById(R.id.btnRegistrarUsuario);
    }

    public void  inicirSesion(View view){
        Intent registrar=new Intent(this,activity_user_login.class);
        startActivity(registrar);
    }
}
