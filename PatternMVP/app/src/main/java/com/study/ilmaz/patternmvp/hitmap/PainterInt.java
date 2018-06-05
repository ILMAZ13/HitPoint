package com.study.ilmaz.patternmvp.hitmap;

import android.app.Activity;
import android.view.MotionEvent;

public interface PainterInt {
    void takeScreenshot(Activity activity);
    void dispatchTouchEvent(MotionEvent ev);
}
