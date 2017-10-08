package com.yxzandra.cornershopchallenge.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xpectra on 6/10/2017.
 */

public class Utils {
    private static Gson mGsonBuilder;

    public static Gson getGsonBuilder() {
        if (mGsonBuilder == null) {
            return new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
        }

        return mGsonBuilder;
    }

}
