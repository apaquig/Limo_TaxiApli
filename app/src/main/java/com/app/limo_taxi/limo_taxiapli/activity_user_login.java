package com.app.limo_taxi.limo_taxiapli;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_user_login extends AppCompatActivity {

    private EditText usuario,password;
    private Button btnIniciarSesion;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mAuth = FirebaseAuth.getInstance();

        usuario=(EditText)findViewById(R.id.txtUserLogin);
        password=(EditText)findViewById(R.id.txtUserPass);
        btnIniciarSesion=(Button)findViewById(R.id.btnIUserLogin);

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(activity_user_login.this, activity_user_map.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               iniciar();
            }
        });
    }
    private void iniciar(){
        String email = usuario.getText().toString();
        String pass = password.getText().toString();
       mAuth.createUserWithEmailAndPassword(email,pass)
               .addOnCompleteListener(activity_user_login.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       Toast.makeText(activity_user_login.this,"Logeado",Toast.LENGTH_LONG).show();
                       if (!task.isSuccessful()){
                           Toast.makeText(activity_user_login.this,"Failed",Toast.LENGTH_LONG).show();
                       }
                   }
               });
    }


    public void registerUser(View view){
        Intent login =new Intent(this, activity_user_register.class);
        startActivity(login);

    }
}
