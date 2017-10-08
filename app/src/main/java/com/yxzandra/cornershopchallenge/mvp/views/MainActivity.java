package com.yxzandra.cornershopchallenge.mvp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yxzandra.cornershopchallenge.R;
import com.yxzandra.cornershopchallenge.helpers.CustomMessage;
import com.yxzandra.cornershopchallenge.helpers.EventType;
import com.yxzandra.cornershopchallenge.mvp.presenters.CounterListPresenter;
import com.yxzandra.cornershopchallenge.mvp.presenters.CounterListPresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CountersListView {
    private CounterListPresenter mPresenter;
    private MaterialDialog mDialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvCounter)
    RecyclerView rvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);

        mDialog = CustomMessage.get(this, CustomMessage.TYPE_PROGRESSBAR, "Loading Counters").build();
        rvCounter.setHasFixedSize(true);
        rvCounter.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new CounterListPresenterImpl().init(this);

        mPresenter.loadCounters();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_counter:

                mDialog = CustomMessage.get(this, CustomMessage.TYPE_EDITTEXT, getResources().getString(R.string.dialog_title_add_counter)).build();
                mDialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        mDialog.show();
    }

    @Override
    public void hideProgress() {
        mDialog.hide();
    }

    @Override
    public void loadListCounters(CountersAdapter mAdapter) {
        rvCounter.setAdapter(mAdapter);
    }

    @Override
    public void addCounter(String title) {
        mPresenter.addCounter(title);
    }

    @Override
    public void onClickDecrement(String id) {
        mPresenter.decrementCounter(id);

    }

    @Override
    public void onClickIncrement(String id) {
        mPresenter.incrementCounter(id);
    }

    @Override
    public void onClickDelete(String id) {
        mPresenter.deleteCounter(id);

    }

    @Override
    public void showHttpError(int code) {

    }

    @Subscribe
    public void onEvent(Object[] args) {

        int event = (int) args[0];

        switch (event) {
            case EventType.ONCLICK_COUNTER_DELETE:
                onClickDelete(args[1].toString());
                break;
            case EventType.ONCLICK_COUNTER_ADD:
                onClickIncrement(args[1].toString());
                break;
            case EventType.ONCLICK_COUNTER_MINUS:
                onClickDecrement(args[1].toString());
                break;
            case EventType.ADD_COUNTER:
                addCounter(args[1].toString());
                break;
        }
    }
}
