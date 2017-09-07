package com.a700apps.techmart.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by samir.salah on 9/7/2017.
 */

public class CategoryData {
    @SerializedName("result")
    @Expose
    public Category category;

    @SerializedName("ISResultHasData")
    @Expose
    public int ISResultHasData;

}
