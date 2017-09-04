package com.a700apps.techmart;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import timber.log.Timber;

/**
 * Created by samir salah on 8/14/2017.
 */

public class TechMartApp extends Application {

    private static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        // initialize debug.
        Timber.plant(new Timber.DebugTree());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //  AppUtils.setAppLanguage(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }


    public static Context getAppContext() {
        return appContext;
    }
}
