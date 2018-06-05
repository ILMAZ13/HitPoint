package com.study.ilmaz.patternmvp.hitmap.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

public class TouchDetectView extends FrameLayout {
    public TouchDetectView(@NonNull Context context) {
        super(context);
    }

    public TouchDetectView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchDetectView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Toast.makeText(getContext(), "Dispatch touch event", Toast.LENGTH_SHORT).show();
        return super.dispatchTouchEvent(ev);
    }
}
