package com.a700apps.techmart.ui.screens.splash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.a700apps.techmart.ui.screens.login.LoginActivity;
import com.a700apps.techmart.utils.ActivityUtils;
import com.a700apps.techmart.utils.AppUtils;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by samir salah on 8/13/2017.
 */

public class SplashActivity extends Activity implements SplashView {

    private SplashPresenter mPresenter;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        try {
            AppUtils.getFirebaseToken();
            Log.e("Token", AppUtils.getFirebaseToken());
        }catch (Exception e){

        }

        mPresenter = new SplashPresenter();
        mPresenter.attachView(this);
        mPresenter.setSplashContentView();
        initializeTimer();
    }

    private void initializeTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                mPresenter.activityFinishedSplashTimer();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.activityStarted();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.activityStopped();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    //------------------- Implemented methods-------------------------------------//

    @Override
    public void openLoginRegisterOptionsActivity() {
        ActivityUtils.openActivity(this, LoginActivity.class, null, false);
    }

    @Override
    public void openHomeActivity(String activityIntentName) {

    }

    @Override
    public void startSplashViewTimer() {

        handler.postDelayed(runnable, 2000);

    }

    @Override
    public void stopSplashViewTimer() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void showWrongUSer() {

    }

    @Override
    public void setSplashViewLayout(@LayoutRes int layout) {
        setContentView(layout);
    }

    @Override
    public void openLoginRegisterActivity() {

    }
}
