package com.bwie.yuekao.API;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 周旋
 * 2017/11/23  10:43
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
