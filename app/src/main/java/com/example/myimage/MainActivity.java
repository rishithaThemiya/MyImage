package com.example.myimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int RESULT_LOAD_IMAGE =1;
    private static int IMAGE_REQUEST_CAMERA = 10;
    ImageView imageView;
    Button cameraButton, galleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        cameraButton = findViewById(R.id.camera);
        galleryButton = findViewById(R.id.gallery);
        imageView = findViewById(R.id.imgV);


        cameraButton.setOnClickListener(this);
        galleryButton.setOnClickListener(this);


//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        }

//        cameraButton.setOnClickListener(listener);
//        galleryButton.setOnClickListener(listener);
    }

    public void galary(){
        Intent intent = new Intent(
          Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        startActivityForResult(intent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_REQUEST_CAMERA){
                Bundle bundle =data.getExtras();
                Bitmap bitmap =(Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);
            }
            else if(requestCode == RESULT_OK){
                Uri selectImage = data.getData();
                imageView.setImageURI(selectImage);
            }
        }
    }

    public void camera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent,IMAGE_REQUEST_CAMERA);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera:
                camera();
                break;
            case R.id.gallery:
                galary();
                break;
        }
    }
}



