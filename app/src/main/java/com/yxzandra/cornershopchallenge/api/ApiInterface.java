package com.yxzandra.cornershopchallenge.api;

import com.yxzandra.cornershopchallenge.schemas.Counter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

/**
 * Created by xpectra on 6/10/2017.
 */

public interface ApiInterface {
    @GET("/api/v1/counters")
    Call<List<Counter>> getAllCounters();

    @POST("/api/v1/counter")
    @FormUrlEncoded
    Call<List<Counter>> addCounter(@Field("title") String title);

    @POST("/api/v1/counter/inc")
    @FormUrlEncoded
    Call<List<Counter>> incrementCounter(@Field("id") String id);

    @POST("/api/v1/counter/dec")
    @FormUrlEncoded
    Call<List<Counter>> decrementCounter(@Field("id") String id);

    @HTTP(method = "DELETE", path = "/api/v1/counter", hasBody = true)
    @FormUrlEncoded
    Call<List<Counter>> deleteCounter(@Field("id") String id);
}
