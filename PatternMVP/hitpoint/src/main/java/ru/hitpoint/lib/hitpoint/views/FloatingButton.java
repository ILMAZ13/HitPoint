package ru.hitpoint.lib.hitpoint.views;


import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.Toast;

import ru.hitpoint.lib.hitpoint.InstanceHolder;
import ru.hitpoint.lib.hitpoint.animations.MyTranslateAnimation;

public class    FloatingButton extends FloatingActionButton {
    private float dX;
    private float dY;
    private int lastAction;
    private int screenHeight;
    private int screenWight;

    public FloatingButton(Context context) {
        super(context);
        fillScreenSizes();
    }

    private void fillScreenSizes() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWight = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    public FloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        fillScreenSizes();
    }

    public FloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        fillScreenSizes();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = this.getX() - ev.getRawX();
                dY = this.getY() - ev.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;
            case MotionEvent.ACTION_UP:
                if (lastAction == MotionEvent.ACTION_DOWN && (ev.getEventTime() - ev.getDownTime()) < ViewConfiguration.getLongPressTimeout()) {
                    onButtonClick();
                } else if (lastAction == MotionEvent.ACTION_MOVE){
                    moveToCorner();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float newY = ev.getRawY() + dY;
                float newX = ev.getRawX() + dX;
                if (newY > 0 &&  newY < screenHeight - getHeight()) {
                    this.setY(newY);
                }
                if (newX > 0 && newX < screenWight - getWidth() ){
                    this.setX(newX);
                }
                lastAction = MotionEvent.ACTION_MOVE;
                break;
        }
        return true;
    }

    private void onButtonClick() {
        Toast.makeText(getContext(), "Click on BUTTON", Toast.LENGTH_SHORT).show();
        InstanceHolder.getInstance().setFreeze(true);
    }

    public void moveToCorner() {
        final float position = screenWight - (getWidth() / 5 * 4) - getX();
        TranslateAnimation tAnim = new MyTranslateAnimation(this,0, position, 0, 0);
        clearAnimation();
        startAnimation(tAnim);
    }
}
