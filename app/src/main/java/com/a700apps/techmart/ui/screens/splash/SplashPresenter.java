package com.a700apps.techmart.ui.screens.splash;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.MainPresenter;

/**
 * Created by samir salah on 8/13/2017.
 */

public class SplashPresenter extends MainPresenter<SplashView> {

    void activityStarted() {
        view.startSplashViewTimer();
    }


    void activityStopped() {
        view.stopSplashViewTimer();
    }

    // define which content view
    void setSplashContentView() {
        // user is logged in app and is active.
        view.setSplashViewLayout(R.layout.activity_splash);
    }

    void activityFinishedSplashTimer() {
        if (isDetachView()) return;
        view.openLoginRegisterOptionsActivity();
    }


}
