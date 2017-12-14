package com.example.lemon.hellowold;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    public static final String[] PERMISSIONS_CONTACT = new String[]{
            Manifest.permission.CALL_PHONE} ;
    public static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMyView();
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                testCall(null);
//            }
//        }, 5000);
//        drawBitmapt();
//        drawPoint();
    }

    private void textMyView() {
        MyView myView = (MyView) findViewById(R.id.my_view);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("lemon", "onClick");
            }
        });
    }

    public void testCall(View view){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CONTACTS);
        }else {
            callPhone();
        }
    }

    public void callPhone()
    {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
//        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == REQUEST_CONTACTS)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhone();
            } else
            {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void drawPoint() {
//        imageView1 = (ImageView) findViewById(R.id.img1);
        Bitmap bmp = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        canvas.drawPoint(120, 20, paint);
        paint.setColor(Color.BLUE);
        float[] points1 = new float[]{10, 10, 50, 50, 50, 100, 50, 150};
        canvas.drawPoints(points1, paint);
        paint.setColor(Color.GREEN);
        canvas.drawPoints(points1, 1, 5, paint);
        imageView1.setImageBitmap(bmp);
    }

    private void drawBitmapt() {
//        imageView1 = (ImageView) findViewById(R.id.img1);

        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bmp, 0, 0, null);
        int bmpW = bmp.getWidth();
        int bmpH = bmp.getHeight();
        Rect src = new Rect(0, 0, bmpW, bmpH);
        Rect dst = new Rect(0, bmpH, bmpW * 3, bmpH * 3 + bmpH);
        canvas.drawBitmap(bmp, src, dst, null);
        imageView1.setImageBitmap(bmpBuffer);
    }
}
