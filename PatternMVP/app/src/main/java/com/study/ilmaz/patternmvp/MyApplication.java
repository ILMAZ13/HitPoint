package com.study.ilmaz.patternmvp;


import android.app.Application;

import ru.hitpoint.lib.hitpoint.InstanceHolder;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("CREATED APP");
        startButtonService();
    }

    private void startButtonService() {
        InstanceHolder.getInstance().bindHitPoint(this, "APP_TOKEN");
    }


}
