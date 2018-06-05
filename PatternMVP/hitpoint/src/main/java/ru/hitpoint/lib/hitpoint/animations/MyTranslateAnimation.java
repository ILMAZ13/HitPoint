package ru.hitpoint.lib.hitpoint.animations;


import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class MyTranslateAnimation extends TranslateAnimation {
    private float fromXDelta;
    private float toXDelta;
    private float fromYDelta;
    private float toYDelta;
    private View view;
    private float startX;
    private float startY;

    public MyTranslateAnimation(View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        super(fromXDelta, toXDelta, fromYDelta, toYDelta);
        this.fromXDelta = fromXDelta;
        this.fromYDelta = fromYDelta;
        this.toXDelta = toXDelta;
        this.toYDelta = toYDelta;
        this.view = view;
        this.startX = view.getX();
        this.startY = view.getY();
        setInterpolator(new FastOutSlowInInterpolator());
        setDuration(500);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float newX = startX + (toXDelta - fromXDelta) * interpolatedTime;
        float newY = startY + (toYDelta - fromYDelta) * interpolatedTime;
        view.setX(newX);
        view.setY(newY);
    }
}
