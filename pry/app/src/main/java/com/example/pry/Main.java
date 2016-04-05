package com.example.pry;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {

    private static final String LOG_TAG = "PryLOG"; // имя лога
    Button btnCamera;   // объект кнопка камеры
    Bitmap bmp;         // объект взятого изображения
    private static final int CAM_REQUEST = 1313;    // код реквеста камеры
    private static final int GALLERY_REQUEST = 1;   // код реквеста галлереи
    private static Uri mOutputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // вешаем событие снимка на кнопку
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new btnCameraClick());

        // код для бинда галереи к кнопке галлереи
        Button button = (Button) findViewById(R.id.btnGallery);
        button.setOnClickListener(new btnGalleryClick());
    }


    // метод срабатывающий при получении резуьтата от камеры или галлереи
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent(this, faceDetect.class);

        Uri u = null;
        // если ответ от камеры
        if (requestCode == CAM_REQUEST) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    bmp = (Bitmap) data.getExtras().get("data");
                    // есть изображением в bmp
                    intent.putExtra("bmp", bmp);
                    intent.putExtra("stts", "bmp");
                }
            } else {
                //u = (Uri)data.getExtras().get(MediaStore.EXTRA_OUTPUT);
                //data.getData();
                intent.putExtra("uri", mOutputFileUri);
                intent.putExtra("stts", "uri");
            }

        }

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                u = (Uri) data.getData();
                intent.putExtra("uri", u);
                intent.putExtra("stts", "uri");
            }
        }

        startActivity(intent);

    }

    // событие для кнопки снимка
    class btnCameraClick implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Time time = new Time();
            time.setToNow();
            File checkDir = new File(Environment.getExternalStorageDirectory() + "/pry/Images");

            if(checkDir.exists()) {
                Log.v(LOG_TAG, "directory exist");
            } else {
                Log.v(LOG_TAG, "directory NOT exist");
                checkDir.mkdirs();
                Log.v(LOG_TAG, "directory create");
            }
            File file = new File(Environment.getExternalStorageDirectory()+"/pry/Images", Integer.toString(time.year) + Integer.toString(time.month) + Integer.toString(time.monthDay) + Integer.toString(time.hour) + Integer.toString(time.minute) + Integer.toString(time.second) + ".jpg");
            mOutputFileUri = Uri.fromFile(file);
            camIntent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputFileUri);
            startActivityForResult(camIntent, CAM_REQUEST);
        }
    }

    class btnGalleryClick implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            //photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
            //photoPickerIntent.addCategory(Intent.CATEGORY_OPENABLE);
            //startActivityForResult(intent, SELECT_PICTURE);
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        }
    }
}
