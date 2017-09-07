package com.a700apps.techmart.data.network;

import com.a700apps.techmart.data.model.CategoryData;
import com.a700apps.techmart.data.model.UserData;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by samir.salah on 9/5/2017.
 */

 interface ApiInterface {

    @POST("Register")
    Observable<UserData> registerUser(@Body RequestBody body);

    @POST("login")
    Observable<UserData> loginUser(@Body RequestBody body);

    @POST("LinkedINLogin")
    Observable<UserData> linkedLoginUser(@Body RequestBody body);


    @POST("forgetuserpassword")
    Observable<Error> forgetPassword(@Body RequestBody body);

   @POST("GetCategory")
   Observable<CategoryData> getCategory(@Body RequestBody body);




}
