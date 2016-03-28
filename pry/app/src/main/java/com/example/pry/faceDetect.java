package com.example.pry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.params.Face;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class faceDetect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect);

        ImageView iv = (ImageView) findViewById(R.id.imgPhoto);

        Intent intent = getIntent();
        String status = intent.getStringExtra("stts");

        if(status.contentEquals("bmp")) {
            Bitmap bmp = (Bitmap) intent.getExtras().getParcelable("bmp");
            iv.setImageBitmap(bmp);
        }
        if(status.contentEquals("uri"))
        {
            Uri u = (Uri)intent.getExtras().getParcelable("uri");
            iv.setImageURI(u);
        }
    }

}
