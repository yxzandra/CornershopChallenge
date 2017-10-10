package com.yxzandra.cornershopchallenge.mvp.presenters;

import com.yxzandra.cornershopchallenge.mvp.views.CountersListView;

/**
 * Created by yxzan on 07/10/2017.
 */

public interface CounterListPresenter {
    CounterListPresenter init(CountersListView view, String urlBase);
    void loadCounters();
    void addCounter(String title);
    void deleteCounter(String id);
    void incrementCounter(String id);
    void decrementCounter(String id);

}
