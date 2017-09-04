package com.a700apps.techmart.ui.screens.login;

import android.content.Context;

import com.a700apps.techmart.R;
import com.a700apps.techmart.TechMartApp;
import com.a700apps.techmart.ui.MainPresenter;
import com.a700apps.techmart.utils.Validator;

/**
 * Created by samir salah on 8/14/2017.
 */

public class LoginPresenter extends MainPresenter<LoginView> {

    private Context context;

    void login(String email, String password) {
        // validate email.
        boolean emptyField = Validator.isTextEmpty(email);
        if (emptyField) {
            view.showErrorDialog(R.string.empty_mail_field);
            return;
        }

        boolean validMail = Validator.validEmail(email);
        if (!validMail) {
            view.showErrorDialog(R.string.invalid_email);
            return;
        }

        // check internet connection.
        context = TechMartApp.getAppContext();
        boolean internetAvailable = isInternetAvailable();
        if (!internetAvailable) {
            view.showErrorDialog(R.string.no_internet_connection);
            return;
        }


//        String deviceId = AppUtils.getDeviceId();
//        String firebaseToken = AppUtils.getFirebaseToken();
//        view.showProgress();
//        login(email, password, appId, deviceId, firebaseToken);
    }
}
