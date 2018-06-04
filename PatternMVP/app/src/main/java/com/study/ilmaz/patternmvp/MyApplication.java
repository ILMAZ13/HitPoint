package com.study.ilmaz.patternmvp;


import android.app.Application;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import com.study.ilmaz.patternmvp.hitmap.InstanceHolder;

import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("CREATED APP");
        startButtonService();
    }

    private List<String> findViewsAt(ViewGroup viewGroup, int x, int y) {
        List<String> ids = new LinkedList<>();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                ids.addAll(findViewsAt((ViewGroup) child, x, y));
            } else {
                int[] location = new int[2];
                child.getLocationOnScreen(location);
                Rect rect = new Rect(location[0], location[1], location[0] + child.getWidth(), location[1] + child.getHeight());
                if (rect.contains(x, y)) {
                    try {
                        ids.add(child.getResources().getResourceName(child.getId()));
                    } catch (Exception e) {
                        ids.add(e.getMessage() + " " + child.getId());
                    }
                }
            }
        }
        return ids;
    }


    private void startButtonService() {
        registerActivityLifecycleCallbacks(InstanceHolder.getInstance().getLifecycleCallbacks());
    }


}
