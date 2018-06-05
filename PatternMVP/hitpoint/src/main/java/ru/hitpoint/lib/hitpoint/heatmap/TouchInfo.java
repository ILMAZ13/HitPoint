package ru.hitpoint.lib.hitpoint.heatmap;

import android.view.MotionEvent;


public class TouchInfo {
    private float x;
    private float y;
    private int action;

    public TouchInfo(MotionEvent event) {
        this.x = event.getX();
        this.y = event.getY();
        this.action = event.getAction();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getAction() {
        return action;
    }
}
