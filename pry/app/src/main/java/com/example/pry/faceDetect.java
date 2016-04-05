package com.example.pry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.camera2.params.Face;
import android.media.FaceDetector;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class faceDetect extends AppCompatActivity{

    private FaceOverlayView mFaceOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect);

        //ImageView iv;// = (ImageView) findViewById(R.id.imgPhoto);
        Bitmap bitmap = null;

        Intent intent = getIntent();
        String status = intent.getStringExtra("stts");

        if(status.contentEquals("bmp")) {
            bitmap = (Bitmap) intent.getExtras().getParcelable("bmp");
        //    iv.setImageBitmap(bmp);
        }
        if(status.contentEquals("uri"))
        {
            Uri u = (Uri)intent.getExtras().getParcelable("uri");
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), u);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mFaceOverlayView = (FaceOverlayView) findViewById( R.id.face_overlay );
        mFaceOverlayView.setBitmap(bitmap);
        mFaceOverlayView.setOnTouchListener(new ClickFace());// setOnTouchListener(this);
        //mFaceOverlayView.setOnTouchListener(this);
    }

    class ClickFace implements View.OnTouchListener {
        //@Override
        public boolean onTouch(View view, MotionEvent event) {
            mFaceOverlayView = (FaceOverlayView) findViewById( R.id.face_overlay );
            int x = (int) event.getX();
            int y = (int) event.getY();
            Log.v("Mytag",  Integer.toString(x) + " " +  Integer.toString(y));//Для того, чтобы можно было отслеживать эти действия, записываем всю информацию о них в лог.
            mFaceOverlayView.chooseFace(x, y);
            return false;
        }
    }
    /*class ClickFace implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mFaceOverlayView = (FaceOverlayView) findViewById( R.id.face_overlay );
            int x=v.getLeft(); // найдено в справке, java ругается на эти функции.
            int y=v.getTop();
            Log.v("Mytag",  Integer.toString(x) + " " +  Integer.toString(y));//Для того, чтобы можно было отслеживать эти действия, записываем всю информацию о них в лог.

        }
    }
    */
    /*
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("This is an alert with no consequence" + x + " " + y);
        dlgAlert.setTitle("App Title");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                mFaceOverlayView = (FaceOverlayView) findViewById( R.id.face_overlay );
                mFaceOverlayView.chooseFace();
        }

        return true;
    }*/
}
