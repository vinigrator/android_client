package com.example.pry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class faceDetect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect);

        ImageView iv = (ImageView) findViewById(R.id.imgPhoto);

        Intent intent = getIntent();
        Bitmap bmp = (Bitmap)intent.getExtras().getParcelable("bmp");
        iv.setImageBitmap(bmp);
    }
}
