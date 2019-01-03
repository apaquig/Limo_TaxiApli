package com.app.limo_taxi.limo_taxiapli;

import android.accounts.AccountAuthenticatorActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_user_register extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText cedula;
    private EditText telefono;
    private EditText correo;
    private EditText password;
    private Button registar;

    private FirebaseAuth mAuth;
    private ProgressDialog mProgres;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        nombre= (EditText)findViewById(R.id.txtUserNombre);
        apellido=(EditText)findViewById(R.id.txtUserAppellido);
        cedula=(EditText)findViewById(R.id.txtUserCedula);
        telefono=(EditText)findViewById(R.id.txtUserTelefono);
        correo=(EditText)findViewById(R.id.txtCorreo);
        password=(EditText)findViewById(R.id.txtPassword);
        registar=(Button)findViewById(R.id.btnUserRegister);

        mAuth=FirebaseAuth.getInstance();
        mProgres=new ProgressDialog(this);
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registarUsuario();
            }

        });
    }

    private void registarUsuario() {
        final String name=nombre.getText().toString();
        final String ape=apellido.getText().toString();
        final String ced=cedula.getText().toString();
        final String tel=telefono.getText().toString();
        final String corr=correo.getText().toString();
        final String pass=password.getText().toString();

        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(ape)&&!TextUtils.isEmpty(ced)&&!TextUtils.isEmpty(tel)&&!TextUtils.isEmpty(corr)&&!TextUtils.isEmpty(pass)){
            mProgres.setMessage("Registrando, pofavor espere...");
            mProgres.show();
            mAuth.createUserWithEmailAndPassword(corr,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgres.dismiss();
                            if (task.isSuccessful()) {
                                mAuth.signInWithEmailAndPassword(corr, pass);

                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("User");
                                DatabaseReference currentUserDB = mDatabase.child(mAuth.getCurrentUser().getUid());
                                currentUserDB.child("nombre").setValue(name);
                                currentUserDB.child("apellido").setValue(ape);
                                currentUserDB.child("cedula").setValue(ced);
                                currentUserDB.child("telefono").setValue(tel);
                                String userid = mAuth.getCurrentUser().getUid();
                                Toast.makeText(activity_user_register.this, "Registrado correctamente", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity_user_register.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }


    public void loginUser(View view){
        Intent login =new Intent(this, activity_user_login.class);
        startActivity(login);

    }
}
