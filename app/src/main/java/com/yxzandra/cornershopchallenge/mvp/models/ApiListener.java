package com.yxzandra.cornershopchallenge.mvp.models;

/**
 * Created by yxzan on 07/10/2017.
 */

public interface ApiListener<T> {
    void successApi(T result);
    void errorApi(int code);
}
