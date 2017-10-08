package com.yxzandra.cornershopchallenge.mvp.models;

/**
 * Created by yxzan on 07/10/2017.
 */

public interface CounterInterface {
    CounterInterface init(ApiListener listener);
    void getAllCounters();
    void addCounter(String title);
    void incrementCounter(String id);
    void decrementCounter(String id);
    void deleteCounter(String id);
}
