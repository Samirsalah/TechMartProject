package com.a700apps.techmart.ui.screens.login;

import android.content.Context;

import com.a700apps.techmart.R;
import com.a700apps.techmart.TechMartApp;
import com.a700apps.techmart.data.model.UserData;
import com.a700apps.techmart.data.network.MainApi;
import com.a700apps.techmart.data.network.MainApiHelper;
import com.a700apps.techmart.data.network.NetworkResponse;
import com.a700apps.techmart.data.network.NetworkResponseListener;
import com.a700apps.techmart.ui.MainPresenter;
import com.a700apps.techmart.utils.AppUtils;
import com.a700apps.techmart.utils.Validator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samir salah on 8/14/2017.
 */

public class LoginPresenter extends MainPresenter<LoginView> implements NetworkResponseListener<UserData> {

    private Context context;

    void login(String email, String password) {


        // check internet connection.
        context = TechMartApp.getAppContext();



        String deviceId = AppUtils.getDeviceId();
        String firebaseToken = AppUtils.getFirebaseToken();
        view.showProgress();
        login(email, password, deviceId, firebaseToken);
    }

    private void login(String email, String password, String deviceId, String pnToken) {
        try {
            JSONObject body = MainApiHelper.createLoginBody(email, password, deviceId, pnToken);
            MainApi.loginUser(body, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void networkOperationSuccess(NetworkResponse<UserData> networkResponse) {
        if(isDetachView()) return;
        view.dismissProgress();
        UserData userNetworkData = networkResponse.data;
        int errorCode = userNetworkData.ISResultHasData;
        if (errorCode==1 ){
            view.openHomeActivity();
        }
    }

    @Override
    public void networkOperationFail(Throwable throwable) {

    }
}
