package com.app.limo_taxi.limo_taxiapli;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class activity_driver_register extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView foto_gallery;

    private EditText nombre,placa,correo,contrasenia;
    private Button registar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);

        foto_gallery = (ImageView)findViewById(R.id.imagenId);
        nombre = (EditText)findViewById(R.id.txtNombre);
        placa= (EditText)findViewById(R.id.txtPlaca);
        correo = (EditText)findViewById(R.id.txtCorreo);
        contrasenia = (EditText)findViewById(R.id.txtContasenia);
        registar = (Button)findViewById(R.id.btnRegistrar);


        foto_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            foto_gallery.setImageURI(imageUri);
        }
    }
public void registrar(){


}
    public void login(View view){
        Intent login =new Intent(this, activity_driver_login.class);
        startActivity(login);

    }


}
