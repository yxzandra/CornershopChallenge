package com.yxzandra.cornershopchallenge.mvp.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxzandra.cornershopchallenge.R;
import com.yxzandra.cornershopchallenge.schemas.Counter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yxzan on 07/10/2017.
 */

public class CountersAdapter extends RecyclerView.Adapter<CountersAdapter.CountersHolder> {

    private List<Counter> mLista;

    public CountersAdapter(List<Counter> lista) {
        mLista = lista;
    }

    @Override
    public CountersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_counter
                , parent, false);

        return new CountersHolder(v);
    }

    @Override
    public void onBindViewHolder(CountersHolder holder, int position) {
        holder.tvTitleCounter.setText(mLista.get(position).getId());
        holder.tvCounter.setText(String.valueOf(mLista.get(position).getCount()));
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }

    public class CountersHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivDelete)
        ImageView ivDelete;
        @BindView(R.id.tvTitleCounter)
        TextView tvTitleCounter;
        @BindView(R.id.ivMinus)
        ImageView ivMinus;
        @BindView(R.id.tvCounter)
        TextView tvCounter;
        @BindView(R.id.ivAdd)
        ImageView ivAdd;

        public CountersHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}

