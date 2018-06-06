package ru.hitpoint.lib.hitpoint;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ru.hitpoint.lib.hitpoint.heatmap.Painter;
import ru.hitpoint.lib.hitpoint.heatmap.PainterInt;
import ru.hitpoint.lib.hitpoint.views.FloatingView;
import ru.hitpoint.lib.hitpoint.views.FloatingViewConfigs;
import ru.hitpoint.lib.hitpoint.views.StopView;
import ru.hitpoint.lib.hitpoint.views.TouchDetectView;

public class HitPoint {
    private static HitPoint ourInstance;
    private Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private boolean isFreeze = false;

    private FloatingViewConfigs floatingViewConfigs = FloatingViewConfigs.newBuilder().build();
    private PainterInt painter = Painter.newBuilder().build();
    private boolean usePainter = true;
    private boolean useFloatingView = true;
    private String token;

    public static HitPoint getInstance() {
        return ourInstance;
    }

    private HitPoint() {
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        isFreeze = freeze;
    }

    public void bindHitPoint(Application application){
        application.registerActivityLifecycleCallbacks(getLifecycleCallbacks());
    }

    private Application.ActivityLifecycleCallbacks getLifecycleCallbacks() {
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
                    ViewGroup mainViewGroup = (ViewGroup) activity.getWindow().getDecorView();
                    if (!(mainViewGroup.getChildAt(0) instanceof StopView)) {
                        FrameLayout stopView = new StopView(activity);
                        FrameLayout touchDetectView = new TouchDetectView(activity);

                        FrameLayout.LayoutParams matchParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        FrameLayout.LayoutParams wrapParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

                        FrameLayout floatingButton = new FloatingView(activity, floatingViewConfigs);

                        View children = mainViewGroup.getChildAt(0);
                        mainViewGroup.removeViewAt(0);
                        touchDetectView.addView(children, 0, matchParams);
                        stopView.addView(touchDetectView, 0, matchParams);
                        if (useFloatingView) {
                            stopView.addView(floatingButton, 1, wrapParams);
                        }
                        mainViewGroup.addView(stopView, 0, children.getLayoutParams());
                    }
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    if (usePainter) {
                        CheckPermissionService checkPermissionService = new CheckPermissionService(activity);
                        if (checkPermissionService.checkPermissionsStorage()) {
                            painter.takeScreenshot(activity);
                        }
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

    public static Builder newBuilder(){
        return new HitPoint().new Builder();
    }


    public class Builder{
        private Builder() {
        }

        public Builder setCustomPainter(PainterInt painter){
            HitPoint.this.painter = painter;
            return this;
        }

        public Builder setPainter(Painter painter){
            HitPoint.this.painter = painter;
            return this;
        }

        public Builder setFloatingViewConfigs(FloatingViewConfigs configs){
            HitPoint.this.floatingViewConfigs = configs;
            return this;
        }

        public Builder setUseHeatMap(boolean useHeatMap){
            HitPoint.this.usePainter = useHeatMap;
            return this;
        }

        public Builder useFloatingView(boolean useFloatingView){
            HitPoint.this.useFloatingView = useFloatingView;
            return this;
        }

        public Builder setToken(String token){
            HitPoint.this.token = token;
            return this;
        }

        public HitPoint build(){
            ourInstance = HitPoint.this;
            return HitPoint.this;
        }
    }
}
