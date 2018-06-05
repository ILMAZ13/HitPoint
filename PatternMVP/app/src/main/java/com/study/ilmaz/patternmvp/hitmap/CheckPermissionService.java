package com.study.ilmaz.patternmvp.hitmap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by hlopu on 28.03.2018.
 */

public class CheckPermissionService {
    public static final int REQUEST_PERMISSION = 777;
    private Context context;

    public CheckPermissionService(Context context) {
        this.context = context;
    }

    public boolean checkPermissionsStorage(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    //TODO тут сразу 2 в одном
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
//            dialog.dismiss();
            return false;
        }
        else{
            return true;
        }
    }
}
