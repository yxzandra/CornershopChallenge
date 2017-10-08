package com.yxzandra.cornershopchallenge.mvp.views;

/**
 * Created by yxzan on 07/10/2017.
 */

public interface CountersListView {
    void showProgress();
    void hideProgress();
    void onClickAddCounter();
    void loadListCounters(CountersAdapter mAdapter);
    void addCounter(String title);
    void onClickDecrement(String id);
    void onClickIncrement(String id);
    void onClickDelete(String id);
    void showHttpError(int code);
}
