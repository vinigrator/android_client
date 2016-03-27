package com.example.pry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class Main extends AppCompatActivity {

    private static final String LOG_TAG = "PryLOG"; // имя лога
    Button btnCamera;   // объект кнопка камеры
    Bitmap bmp;         // объект взятого изображения
    private static final int CAM_REQUEST = 1313;    // код реквеста камеры
    private static final int GALLERY_REQUEST = 1;   // код реквеста галлереи

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // вешаем событие снимка на кнопку
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new btnCameraClick());

        // код для бинда галереи к кнопке галлереи
        Button button = (Button)findViewById(R.id.btnGallery);
        button.setOnClickListener(new btnGalleryClick());
    }


    // метод срабатывающий при получении резуьтата от камеры или галлереи
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data==null)
            return;

        // если ответ от камеры
        if(requestCode == CAM_REQUEST)
        {
            if(data.hasExtra("data")) {
                bmp = (Bitmap) data.getExtras().get("data");
                // есть изображением в bmp

            }

        }else if(requestCode == GALLERY_REQUEST)
        {
            if(resultCode == RESULT_OK){
                Uri selectedImage = data.getData();
                try {
                    bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // есть изображение в bmp
            }
        }

        Intent intent = new Intent(Main.this, faceDetect.class);
        intent.putExtra("bmp", bmp);
        startActivity(intent);
    }

    // событие для кнопки снимка
    class btnCameraClick implements Button.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camIntent, CAM_REQUEST);
        }
    }

    class btnGalleryClick implements  Button.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        }
    }
}
