package com.a700apps.techmart.ui.screens.register;

import com.a700apps.techmart.data.model.UserData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samir salah on 8/16/2017.
 */

public class RegisterPresenter extends MainPresenter<RegisterView> implements NetworkResponseListener<UserData> {



    void register(String fullName, String password, String email, String mobileNumber, String referralCode) {
        boolean emptyName = Validator.isTextEmpty(fullName);
        if (emptyName) {
            view.showErrorDialog(R.string.name_length_not_valid,R.string.name_length_not_valid);
            return;
        }
        boolean validName = Validator.validName(fullName);
        if (!validName) {
            view.showErrorDialog(R.string.operation_failed,R.string.invaild_name);
            return;
        }
        boolean validNameSpecial = Validator.validNamespecila(fullName);
        if (!validNameSpecial) {
            view.showErrorDialog(R.string.operation_failed,R.string.specialcharacter_not);
            return;
        }

        boolean validEmail = Validator.validEmail(email);
        if (!validEmail) {
            view.showErrorDialog(R.string.operation_failed,R.string.invalid_email);
            return;
        }

        boolean validPassword = Validator.validPassword(password);
        if (!validPassword) {
            view.showErrorDialog(R.string.invalid_password_length,R.string.invalid_password);
            return;
        }

        boolean validMobileNumber = Validator.validMobileNumber(mobileNumber.replaceFirst("\\+", ""));
        if (!validMobileNumber) {
            view.showErrorDialog(R.string.operation_failed,R.string.invalid_mobile_number);
            return;
        }

        if (!AppUtils.isInternetAvailable(BabySitterApp.getAppContext())) {
            view.showErrorDialog(R.string.operation_failed,R.string.no_internet_connection);
            return;
        }

        view.showLoadingProgress();
        String userType = AppConstant.UsersTypes.SITTER;
        if (AppUtils.isParentUser(BabySitterApp.getAppContext())) {
            userType = AppConstant.UsersTypes.PARENT;
        }
        String deviceId = AppUtils.getDeviceId();
        String firebaseToken = AppUtils.getFirebaseToken();
        try {
            JSONObject registerBody = MainApiHelper.createRegisterBody(fullName, password, email, mobileNumber, referralCode,
                    AppConstant.RegisterViaTypes.APP_REGISTER, userType, deviceId, firebaseToken);
            MainApi.registerUser(registerBody, this);
        } catch (JSONException e) {
            e.printStackTrace();
            view.dismissLoadingProgress();
            view.showErrorDialog(R.string.operation_failed,R.string.cannot_register);
        }

    }

}
