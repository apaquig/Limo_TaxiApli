package com.app.limo_taxi.limo_taxiapli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_user_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }
    public void registerUser(View view){
        Intent login =new Intent(this, activity_user_register.class);
        startActivity(login);

    }
}
