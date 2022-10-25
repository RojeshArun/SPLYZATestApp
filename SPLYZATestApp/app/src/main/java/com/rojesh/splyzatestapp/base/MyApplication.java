package com.rojesh.splyzatestapp.base;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = (ApplicationComponent) this;
    }

    public static ApplicationComponent getApplicationComponent(Context context) {
        return ((MyApplication) context.getApplicationContext()).component;
    }
}
