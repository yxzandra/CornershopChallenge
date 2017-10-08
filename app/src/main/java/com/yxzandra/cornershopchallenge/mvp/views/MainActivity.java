package com.yxzandra.cornershopchallenge.mvp.views;

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
import com.yxzandra.cornershopchallenge.mvp.presenters.CounterListPresenter;
import com.yxzandra.cornershopchallenge.mvp.presenters.CounterListPresenterImpl;

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
                break;
        }

        return super.onOptionsItemSelected(item);
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
    public void onClickAddCounter() {

    }

    @Override
    public void loadListCounters(CountersAdapter mAdapter) {
        rvCounter.setAdapter(mAdapter);

    }

    @Override
    public void addCounter(String title) {

    }

    @Override
    public void onClickDecrement(String id) {

    }

    @Override
    public void onClickIncrement(String id) {

    }

    @Override
    public void onClickDelete(String id) {

    }

    @Override
    public void showHttpError(int code) {

    }
}
