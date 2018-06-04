package com.study.ilmaz.patternmvp.hitmap.views;


import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.study.ilmaz.patternmvp.hitmap.InstanceHolder;

import java.util.LinkedList;
import java.util.List;

public class StopView extends FrameLayout {

    public StopView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return InstanceHolder.getInstance().isFreeze() || super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (InstanceHolder.getInstance().isFreeze()){
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                        onViewSelected(ev);
                        return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    private void onViewSelected(MotionEvent ev) {
        List<String> ids = findViewsAt((ViewGroup) ((ViewGroup) this.getChildAt(1)).getChildAt(0), (int) ev.getX(), (int) ev.getY());
        InstanceHolder.getInstance().setFreeze(false);
        CommentaryDialogue commentaryDialogue = new CommentaryDialogue(getContext());
        commentaryDialogue.show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
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
                        String id = child.getResources().getResourceName(child.getId());
                        if (id.isEmpty()){
                            id = child.getClass().getName();
                        }
                        ids.add(id);
                    } catch (Exception e) {
                        ids.add(e.getMessage() + " " + child.getId());
                    }
                }
            }
        }
        return ids;
    }
}
