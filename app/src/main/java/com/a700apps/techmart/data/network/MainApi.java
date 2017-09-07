package com.a700apps.techmart.data.network;

import android.support.annotation.NonNull;

import com.a700apps.techmart.data.model.UserData;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by samir.salah on 9/5/2017.
 */

public class MainApi {

    private static final String API_LINK = "http://108.179.204.213:8073/api/";
    private static final String JSON_TYPE = "application/json";

    private static ApiInterface getApi() {
        //Retrofit interceptor.
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build();
        // Retrofit.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_LINK)
                .client(client).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    @NonNull
    private static RequestBody getRequestBody(JSONObject jsonBody) {
        return RequestBody.create(MediaType.parse(JSON_TYPE), jsonBody.toString());
    }

    public static void registerUser(JSONObject jsonBody, final NetworkResponseListener<UserData> responseListener) {
        RequestBody requestBody = getRequestBody(jsonBody);
        getApi().registerUser(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserData>() {

                    @Override
                    public void onNext(UserData value) {
                        final NetworkResponse<UserData> networkResponse = new NetworkResponse<>();
//                        networkResponse.requestType = AppConstant.NetworkOperationsTypes.REGISTER;
                        networkResponse.data = value;
                        responseListener.networkOperationSuccess(networkResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        responseListener.networkOperationFail(e);
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    public static void loginUser(JSONObject body, final NetworkResponseListener<UserData> responseListener) {
        getApi().loginUser(getRequestBody(body)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                responseListener.networkOperationFail(e);
            }

            @Override
            public void onNext(UserData userNetworkData) {
                NetworkResponse<UserData> networkResponse = new NetworkResponse<>();
                networkResponse.data = userNetworkData;
                responseListener.networkOperationSuccess(networkResponse);
            }
        });
    }


}
