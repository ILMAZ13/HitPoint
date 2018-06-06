package ru.hitpoint.lib.hitpoint.heatmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.MotionEvent;

public interface PainterInt {
    Bitmap takeScreenshot(Activity activity);
    void dispatchTouchEvent(MotionEvent ev);
}
