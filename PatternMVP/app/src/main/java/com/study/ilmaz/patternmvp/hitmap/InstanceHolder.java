package com.study.ilmaz.patternmvp.hitmap;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.study.ilmaz.patternmvp.R;
import com.study.ilmaz.patternmvp.hitmap.views.StopView;
import com.study.ilmaz.patternmvp.hitmap.views.TouchDetectView;

public class InstanceHolder {
    private static final InstanceHolder ourInstance = new InstanceHolder();
    private PainterInt painter;
    private Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private boolean isFreeze = false;

    public static InstanceHolder getInstance() {
        return ourInstance;
    }

    private InstanceHolder() {
        painter = new Painter();
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        isFreeze = freeze;
    }

    public Application.ActivityLifecycleCallbacks getLifecycleCallbacks() {
        if (lifecycleCallbacks == null) {
            lifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {
                    ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
                    if (!(viewGroup.getChildAt(0) instanceof StopView)) {
                        FrameLayout stopView = new StopView(activity);
                        FrameLayout touchDetectView = new TouchDetectView(activity);
                        activity.getLayoutInflater().inflate(R.layout.floating_button, stopView);

                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                        View children = viewGroup.getChildAt(0);
                        viewGroup.removeViewAt(0);
                        touchDetectView.addView(children, 0, layoutParams);
                        stopView.addView(touchDetectView, 1, layoutParams);
                        viewGroup.addView(stopView, 0, children.getLayoutParams());
                    }
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    CheckPermissionService checkPermissionService = new CheckPermissionService(activity);
                    if (checkPermissionService.checkPermissionsStorage()) {
                        painter.takeScreenshot(activity);
                    }
                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            };
        }
        return lifecycleCallbacks;
    }

    public void addTouch(MotionEvent motionEvent) {
        painter.dispatchTouchEvent(motionEvent);
    }

}
