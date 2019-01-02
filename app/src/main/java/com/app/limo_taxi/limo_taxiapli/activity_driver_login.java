package com.app.limo_taxi.limo_taxiapli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class activity_driver_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

    }
    public void  registrar(View view){
        Intent registrar=new Intent(this,activity_driver_register.class);
        startActivity(registrar);
    }
}
