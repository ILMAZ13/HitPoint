package com.study.ilmaz.patternmvp;


import android.app.Application;

import ru.hitpoint.lib.hitpoint.HitPoint;
import ru.hitpoint.lib.hitpoint.heatmap.Painter;
import ru.hitpoint.lib.hitpoint.views.FloatingViewConfigs;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("CREATED APP");
        startButtonService();
    }

    private void startButtonService() {
        HitPoint.newBuilder()
                .setFloatingViewConfigs(FloatingViewConfigs.newBuilder()
                        .setUseGravity(false)
                        .build()
                )
                .setPainter(Painter.newBuilder()
                        .setPointWidth(10)
                        .setBackgroundArgb(100, 0, 0, 0)
                        .setPointArgb(50, 255, 0, 0)
                        .build()
                )
                .build()
                .bindHitPoint(this);
    }


}
