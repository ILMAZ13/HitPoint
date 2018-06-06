package ru.hitpoint.lib.hitpoint.views;


import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import ru.hitpoint.lib.hitpoint.HitPoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StopView extends FrameLayout {

    public StopView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return HitPoint.getInstance().isFreeze() || super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (HitPoint.getInstance().isFreeze()){
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                        onViewSelected(ev);
                        return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    private void onViewSelected(MotionEvent ev) {
        List<String> ids = findViewsAt((ViewGroup) ((ViewGroup) this.getChildAt(0)).getChildAt(0), (int) ev.getX(), (int) ev.getY());
        Log.d("PATH", Arrays.toString(ids.toArray(new String[0])));
        HitPoint.getInstance().setFreeze(false);
        CommentaryDialogue commentaryDialogue = new CommentaryDialogue(getContext(), Arrays.toString(ids.toArray(new String[0])));
        commentaryDialogue.show();
        Window window = commentaryDialogue.getWindow();
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    private List<String> findViewsAt(ViewGroup viewGroup, int x, int y) {
        List<String> ids = new LinkedList<>();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
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
                    ids.add(child.getClass().getName());
                }
            }
            if (child instanceof ViewGroup) {
                ids.addAll(findViewsAt((ViewGroup) child, x, y));
            }
        }
        return ids;
    }
}
