package com.study.ilmaz.patternmvp.hitmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.study.ilmaz.patternmvp.hitmap.views.TouchInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Painter {
    private List<TouchInfo> motionEvents = new ArrayList<>();


    public void dispatchTouchEvent(MotionEvent ev) {
        Log.d("FOR TOLYA CHECK ", "PRIVET");
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            motionEvents.add(new TouchInfo(ev));
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            motionEvents.add(new TouchInfo(ev));
        }
        Log.d("MOTION EVENT ", String.valueOf(ev.getAction()));
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            motionEvents.add(new TouchInfo(ev));
        }

    }

    public void takeScreenshot(Activity activity) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = activity.getWindow().getDecorView().getRootView();
//            v1.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height)
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            Bitmap bitmap = Bitmap.createBitmap(v1.getLayoutParams().width , v1.getLayoutParams().height,
//                    Bitmap.Config.ARGB_8888);
            v1.setDrawingCacheEnabled(false);
            Canvas canvas = new Canvas(bitmap);
            Paint point = new Paint();
            Paint background = new Paint();
            point.setARGB(50, 237, 126, 23);
            point.setStrokeWidth(45);
            background.setARGB(60, 0, 0, 255);
            canvas.drawRect(0.0f, 0.0f, Float.parseFloat(Integer.toString(v1.getWidth())),
                    Float.parseFloat(Integer.toString(v1.getHeight())), background);

//            canvas.drawColor(Color.BLUE, PorterDuff.Mode.LIGHTEN);
            float start = 0;
            float finish = 0;
            for (TouchInfo ev : motionEvents) {
                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                    start = ev.getX();
                    finish = ev.getY();
                    canvas.drawCircle(start, finish, 50, point);

                } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                    canvas.drawLine(start, finish, ev.getX(), ev.getY(), point);
                    start = ev.getX();
                    finish = ev.getY();
                } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                    canvas.drawLine(start, finish, ev.getX(), ev.getY(), point);
                    start = 0;
                    finish = 0;
                }

            }

            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            motionEvents.clear();

//            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

//    private void openScreenshot(File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(imageFile);
//        intent.setDataAndType(uri, "image/*");
//        activity.startActivity(intent);
//    }
}
