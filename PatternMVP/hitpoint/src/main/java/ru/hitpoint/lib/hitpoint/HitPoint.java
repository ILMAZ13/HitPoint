package ru.hitpoint.lib.hitpoint;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.entities.HitPointAPI;
import model.entities.ViewsBlock;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.hitpoint.lib.hitpoint.heatmap.Painter;
import ru.hitpoint.lib.hitpoint.heatmap.PainterInt;
import ru.hitpoint.lib.hitpoint.network.ApiService;
import ru.hitpoint.lib.hitpoint.network.NetServiceManager;
import ru.hitpoint.lib.hitpoint.views.FloatingView;
import ru.hitpoint.lib.hitpoint.views.FloatingViewConfigs;
import ru.hitpoint.lib.hitpoint.views.StopView;
import ru.hitpoint.lib.hitpoint.views.TouchDetectView;

public class HitPoint {
    public static final String API_KEY = "d3e6d3565cd14621a5aa317b5cc6ed64";
    private static HitPoint ourInstance;
    private Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private boolean isFreeze = false;
    private ApiService apiService;

    private FloatingViewConfigs floatingViewConfigs = FloatingViewConfigs.newBuilder().build();
    private PainterInt painter = Painter.newBuilder().build();
    private boolean usePainter = true;
    private boolean useFloatingView = true;
    private String token;
    private int dialogueColor = Color.argb(255, 102, 158, 58);

    private SharedPreferences preferences;

    public static HitPoint getInstance() {
        return ourInstance;
    }

    private HitPoint() {
        apiService = new NetServiceManager().getApiService();
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        isFreeze = freeze;
    }

    public void bindHitPoint(Application application) {
        preferences = application.getSharedPreferences("HitPoint", Context.MODE_PRIVATE);
        String udid = preferences.getString("UDID", "");
        if (udid.isEmpty()) {
            udid = UUID.randomUUID().toString();
            preferences.edit()
                    .putString("UDID", udid)
                    .apply();
        }
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
                        FrameLayout stopView = new StopView(activity, dialogueColor);
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
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    painter.takeScreenshot(activity).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String encodedBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    List<ViewsBlock> viewsBlocks = new ArrayList<>();
                    apiService.report(new HitPointAPI(preferences.getString("UDID", ""), activity.getClass().getName(), "",
                            "1.0", "", encodedBase64, "0",
                            API_KEY, preferences.getString("UDID", ""),
                            viewsBlocks)).enqueue(new Callback<Request>() {
                        @Override
                        public void onResponse(Call<Request> call, Response<Request> response) {
                            Log.d("REPORT", "SUCCES");
                        }

                        @Override
                        public void onFailure(Call<Request> call, Throwable t) {
                            Log.d("REPORT", "FAILURE");
                        }
                    });
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

    public static Builder newBuilder() {
        return new HitPoint().new Builder();
    }


    public class Builder {
        private Builder() {
        }

        public Builder setCustomPainter(PainterInt painter) {
            HitPoint.this.painter = painter;
            return this;
        }

        public Builder setPainter(Painter painter) {
            HitPoint.this.painter = painter;
            return this;
        }

        public Builder setFloatingViewConfigs(FloatingViewConfigs configs) {
            HitPoint.this.floatingViewConfigs = configs;
            return this;
        }

        public Builder setUseHeatMap(boolean useHeatMap) {
            HitPoint.this.usePainter = useHeatMap;
            return this;
        }

        public Builder useFloatingView(boolean useFloatingView) {
            HitPoint.this.useFloatingView = useFloatingView;
            return this;
        }

        public Builder setToken(String token) {
            HitPoint.this.token = token;
            return this;
        }

        public Builder setCommentaryDialogueColor(int alpha, int r, int g, int b) {
            HitPoint.this.dialogueColor = Color.argb(alpha, r, g, b);
            return this;
        }

        public HitPoint build() {
            ourInstance = HitPoint.this;
            return HitPoint.this;
        }
    }
}
