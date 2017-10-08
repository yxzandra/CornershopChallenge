package com.yxzandra.cornershopchallenge.api;

import com.yxzandra.cornershopchallenge.schemas.Counter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by xpectra on 6/10/2017.
 */

public interface ApiInterface {
    @GET("/api/v1/counters")
    Call<List<Counter>> getAllCounters();

    @POST("/api/v1/counter")
    Call<List<Counter>> addCounter(@Field("title") String title);

    @POST("/api/v1/counter/inc")
    Call<List<Counter>> incrementCounter(@Field("id") String id);

    @POST("/api/v1/counter/dec")
    Call<List<Counter>> decrementCounter(@Field("id") String id);

    @DELETE("/api/v1/counter")
    Call<List<Counter>> deleteCounter(@Field("id") String title);
}