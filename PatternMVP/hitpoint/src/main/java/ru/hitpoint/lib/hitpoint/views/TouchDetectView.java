package ru.hitpoint.lib.hitpoint.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import ru.hitpoint.lib.hitpoint.InstanceHolder;

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
        InstanceHolder.getInstance().addTouch(ev);
        return super.dispatchTouchEvent(ev);
    }
}
