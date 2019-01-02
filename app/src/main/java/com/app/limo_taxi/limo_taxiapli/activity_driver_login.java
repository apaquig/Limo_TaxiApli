package com.app.limo_taxi.limo_taxiapli;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_driver_login extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mLogin, mRegistro;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        mLogin = (Button) findViewById(R.id.login);
        mRegistro = (Button) findViewById(R.id.registro);
        mEmail = (EditText) findViewById(R.id.txtCorreo);
        mPassword = (EditText) findViewById(R.id.txtContrasenia);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(activity_driver_login.this, activity_driver_map.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity_driver_login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(activity_driver_login.this, "Error al Ingresar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
    public void  registrar(View view){
        Intent registrar=new Intent(this,activity_driver_register.class);
        startActivity(registrar);
    }
}
