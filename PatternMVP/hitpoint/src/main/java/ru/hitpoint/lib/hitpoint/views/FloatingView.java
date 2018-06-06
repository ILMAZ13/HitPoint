package ru.hitpoint.lib.hitpoint.views;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.Toast;

import ru.hitpoint.lib.hitpoint.HitPoint;
import ru.hitpoint.lib.hitpoint.R;
import ru.hitpoint.lib.hitpoint.animations.MyTranslateAnimation;

public class FloatingView extends FrameLayout {
    private float dX;
    private float dY;
    private int lastAction;
    private int screenHeight;
    private int screenWight;

    private Gravity buttonGravity;
    private boolean useGravity;

    public FloatingView(Context context, FloatingViewConfigs configs) {
        super(context);
        useGravity = configs.useGravity;
        buttonGravity = configs.buttonGravity;
        if (configs.buttonView == null){
            View.inflate(context, R.layout.floating_button, this);
        } else {
            FrameLayout.LayoutParams wrapParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, android.view.Gravity.CENTER);
            this.addView(configs.buttonView, wrapParams);
        }
        fillScreenSizes();
    }

    private void fillScreenSizes() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWight = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
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
                    if (useGravity) {
                        moveToCorner(buttonGravity);
                    }
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
        Toast.makeText(getContext(), "Choose a View", Toast.LENGTH_SHORT).show();
        HitPoint.getInstance().setFreeze(true);
    }

    public void moveToCorner(Gravity gravity) {
        switch (gravity){

            case LEFT: {
                final float positionX = 0 - (getWidth() / 5) - getX();
                TranslateAnimation tAnim = new MyTranslateAnimation(this, 0, positionX, 0, 0);
                clearAnimation();
                startAnimation(tAnim);
                break;
            }
            case RIGHT: {
                final float positionX = screenWight - (getWidth() / 5 * 4) - getX();
                TranslateAnimation tAnim = new MyTranslateAnimation(this, 0, positionX, 0, 0);
                clearAnimation();
                startAnimation(tAnim);
                break;
            }
            case TOP:{
                final float positionY = 0 - getY();
                TranslateAnimation tAnim = new MyTranslateAnimation(this, 0, 0, 0, positionY);
                clearAnimation();
                startAnimation(tAnim);
                break;
            }
            case BOTTOM:{
                final float positionY = screenHeight - (getHeight() / 5 * 4) - getY();
                TranslateAnimation tAnim = new MyTranslateAnimation(this, 0, 0, 0, positionY);
                clearAnimation();
                startAnimation(tAnim);
                break;
            }
            case CENTER:
                final float positionY = screenHeight / 2 - getHeight() / 2 - getY();
                final float positionX = screenWight / 2 - getWidth() / 2 - getX();
                TranslateAnimation tAnim = new MyTranslateAnimation(this, 0, positionX, 0, positionY);
                clearAnimation();
                startAnimation(tAnim);
                break;
        }
    }

    public enum Gravity{
        LEFT, RIGHT, TOP, BOTTOM, CENTER
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
