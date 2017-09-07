package com.a700apps.techmart.data.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samir.salah on 9/5/2017.
 */

public class MainApiHelper {


    public static JSONObject createRegisterBody(String fullName, String password, String email,String mobile,
                                                String photo, String companyName, String position,
                                                 String deviceId, String pnToken) throws JSONException {
        JSONObject body = new JSONObject();
        body.put("Name", fullName);
        body.put("Password", password);
        body.put("Phone", mobile);
        body.put("Email", email);
        body.put("Photo", photo);
        body.put("Company", companyName);
        body.put("Position", position);
        body.put("DeviceID", deviceId);
        body.put("DeviceToken", pnToken);

        return body;
    }

    public static JSONObject createLoginBody(String email, String password, String deviceId, String firebaseToken) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Password", email);
        jsonObject.put("Email", password);
        jsonObject.put("DeviceID", deviceId);
        jsonObject.put("DeviceToken", firebaseToken);
        return jsonObject;
    }


    public static JSONObject createForgetPasswordBody(String userMail, String appId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", userMail);
        return jsonObject;
    }

}
