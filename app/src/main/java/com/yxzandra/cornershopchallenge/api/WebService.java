package com.yxzandra.cornershopchallenge.api;

import android.content.Context;

import com.yxzandra.cornershopchallenge.R;
import com.yxzandra.cornershopchallenge.helpers.CustomMessage;
import com.yxzandra.cornershopchallenge.helpers.Utils;
import com.yxzandra.cornershopchallenge.schemas.Counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by xpectra on 6/10/2017.
 */

public class WebService {
    private static String TAG = WebService.class.getSimpleName();

    /********************************************************************************
     * PARAMETERS                                                                   *
     ********************************************************************************/
    private static Converter.Factory mConverterFactory;
    private static Map<String, Retrofit> retrofitClients = new HashMap<>();

    /********************************************************************************
     * URLs                                                                         *
     ********************************************************************************/
    //http://192.168.3.151:15937

    // END-POINTS
    private static final String URL_BASE = "http://192.168.0.107:3000";

    /********************************************************************************
     * CONSTRUCTORS                                                                 *
     ********************************************************************************/
    public WebService() {

    }

    /********************************************************************************
     * ADAPTERS                                                                     *
     ********************************************************************************/
    private static Retrofit buildAdapterCustomTimeOut(int timeout) {
        if (mConverterFactory == null) {
            mConverterFactory = GsonConverterFactory.create(Utils.getGsonBuilder());
        }

        String clientKey = String.valueOf(timeout);
        Retrofit retrofitCustomTimeout = retrofitClients.get(clientKey);

        if (retrofitCustomTimeout == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(timeout, TimeUnit.SECONDS)
                    .connectTimeout(timeout, TimeUnit.SECONDS)
                    .build();

            Retrofit mRetrofitCustomTimeout = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(mConverterFactory)
                    .client(okHttpClient)
                    .build();

            retrofitClients.put(clientKey, mRetrofitCustomTimeout);
            return mRetrofitCustomTimeout;
        } else {
            return retrofitCustomTimeout;
        }
    }


    /********************************************************************************
     * SERVICES                                                                     *
     ********************************************************************************/

    public static Call<List<Counter>> getAllCounters() {
        ApiInterface api = buildAdapterCustomTimeOut(15).create(ApiInterface.class);
        return api.getAllCounters();
    }

    public static Call<List<Counter>> addCounter(String title) {
        ApiInterface api = buildAdapterCustomTimeOut(15).create(ApiInterface.class);
        return api.addCounter(title);
    }

    public static Call<List<Counter>> incrementCounter(String id) {
        ApiInterface api = buildAdapterCustomTimeOut(15).create(ApiInterface.class);
        return api.incrementCounter(id);
    }

    public static Call<List<Counter>> decrementCounter(String id) {
        ApiInterface api = buildAdapterCustomTimeOut(15).create(ApiInterface.class);
        return api.decrementCounter(id);
    }

    public static Call<List<Counter>> deleteCounter(String id) {
        ApiInterface api = buildAdapterCustomTimeOut(15).create(ApiInterface.class);
        return api.deleteCounter(id);
    }



}
