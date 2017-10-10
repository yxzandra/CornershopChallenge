package com.yxzandra.cornershopchallenge.mvp.models;


import com.yxzandra.cornershopchallenge.api.WebService;
import com.yxzandra.cornershopchallenge.schemas.Counter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yxzan on 07/10/2017.
 */

public class CounterImpl implements CounterInterface {
    private static final String TAG = CounterImpl.class.getSimpleName();
    ApiListener mListener;

    @Override
    public CounterInterface init(ApiListener listener,String urlBase ) {
        mListener = listener;
        WebService.setUrlBase(urlBase);
        return this;
    }

    @Override
    public void getAllCounters() {
        WebService.getAllCounters().enqueue(new Callback<List<Counter>>() {
            @Override
            public void onResponse(Call<List<Counter>> call, Response<List<Counter>> response) {
                if (response.isSuccessful())
                    mListener.successApi(response.body());
                else
                    mListener.errorApi(response.code());
            }

            @Override
            public void onFailure(Call<List<Counter>> call, Throwable t) {
                mListener.errorApi(400);
            }
        });

    }

    @Override
    public void addCounter(String title) {
        WebService.addCounter(title).enqueue(new Callback<List<Counter>>() {
            @Override
            public void onResponse(Call<List<Counter>> call, Response<List<Counter>> response) {
                if (response.isSuccessful())
                    mListener.successApi(response.body());
                else
                    mListener.errorApi(response.code());
            }

            @Override
            public void onFailure(Call<List<Counter>> call, Throwable t) {
                mListener.errorApi(400);
            }
        });

    }

    @Override
    public void incrementCounter(String id) {
        WebService.incrementCounter(id).enqueue(new Callback<List<Counter>>() {
            @Override
            public void onResponse(Call<List<Counter>> call, Response<List<Counter>> response) {
                if (response.isSuccessful())
                    mListener.successApi(response.body());
                else
                    mListener.errorApi(response.code());
            }

            @Override
            public void onFailure(Call<List<Counter>> call, Throwable t) {
                mListener.errorApi(400);
            }
        });

    }

    @Override
    public void decrementCounter(String id) {
        WebService.decrementCounter(id).enqueue(new Callback<List<Counter>>() {
            @Override
            public void onResponse(Call<List<Counter>> call, Response<List<Counter>> response) {
                if (response.isSuccessful())
                    mListener.successApi(response.body());
                else
                    mListener.errorApi(response.code());
            }

            @Override
            public void onFailure(Call<List<Counter>> call, Throwable t) {
                mListener.errorApi(400);
            }
        });

    }

    @Override
    public void deleteCounter(String id) {
        WebService.deleteCounter(id).enqueue(new Callback<List<Counter>>() {
            @Override
            public void onResponse(Call<List<Counter>> call, Response<List<Counter>> response) {
                if (response.isSuccessful())
                    mListener.successApi(response.body());
                else
                    mListener.errorApi(response.code());
            }

            @Override
            public void onFailure(Call<List<Counter>> call, Throwable t) {
                mListener.errorApi(400);
            }
        });

    }
}
