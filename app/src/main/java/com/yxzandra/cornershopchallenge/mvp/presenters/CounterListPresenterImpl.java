package com.yxzandra.cornershopchallenge.mvp.presenters;


import com.yxzandra.cornershopchallenge.mvp.models.ApiListener;
import com.yxzandra.cornershopchallenge.mvp.models.CounterImpl;
import com.yxzandra.cornershopchallenge.mvp.models.CounterInterface;
import com.yxzandra.cornershopchallenge.mvp.views.CountersAdapter;
import com.yxzandra.cornershopchallenge.mvp.views.CountersListView;
import com.yxzandra.cornershopchallenge.schemas.Counter;

import java.util.List;

/**
 * Created by yxzan on 07/10/2017.
 */

public class CounterListPresenterImpl implements CounterListPresenter, ApiListener<List<Counter>> {

    private CountersListView mView;
    private CounterInterface counterInterface;

    @Override
    public CounterListPresenter init(CountersListView view) {
        mView = view;
        counterInterface = new CounterImpl().init(this);
        return this;
    }

    @Override
    public void loadCounters() {
        mView.showProgress();
        counterInterface.getAllCounters();

    }

    @Override
    public void addCounter(String title) {
        counterInterface.addCounter(title);
    }

    @Override
    public void deleteCounter(String id) {
        counterInterface.deleteCounter(id);

    }

    @Override
    public void incrementCounter(String id) {
        counterInterface.incrementCounter(id);

    }

    @Override
    public void decrementCounter(String id) {
        counterInterface.decrementCounter(id);

    }

    @Override
    public void successApi(List<Counter> result) {
        mView.hideProgress();
        if (result.size() != 0) {
            CountersAdapter mAdapter = new CountersAdapter(result);
            grandTotal(result);
            mView.loadListCounters(mAdapter);
        }else
            mView.loadListCounters(null);

    }

    private void grandTotal(List<Counter> items){

        int totalCount = 0;
        for(Counter counter : items) {
            totalCount += counter.getCount();
        }

        mView.totalCounter(totalCount);
    }

    @Override
    public void errorApi(int code) {
        mView.hideProgress();
        mView.showHttpError(code);
    }
}
