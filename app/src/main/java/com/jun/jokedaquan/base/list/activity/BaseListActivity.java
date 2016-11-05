package com.jun.jokedaquan.base.list.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.activitys.BroadcastActivity;
import com.jun.jokedaquan.widget.pulldownrefresh.RefreshLayout;
import com.jun.jokedaquan.widget.pulldownrefresh.RefreshLayoutHeader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * BaseListActivity
 * Created by Tse on 2016/10/30.
 */

public abstract class BaseListActivity extends BroadcastActivity
        implements RefreshLayout.OnRefreshListener {

    @Bind(R.id.rl)
    RefreshLayout pullDownRefresh;

    @Bind(android.R.id.empty)
    ImageView empty;

    @Bind(android.R.id.list)
    RecyclerView recyclerView;

    @Bind(R.id.base_list_title)
    TextView tvTitle;

    @Bind(R.id.base_list_btn)
    TextView btn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_list;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setSupportActionBar(R.id.base_list_toolbar);
        tvTitle.setText(getMyTitle());
        RefreshLayoutHeader header = new RefreshLayoutHeader(this);
        pullDownRefresh.addOnRefreshListener(header);
        pullDownRefresh.addOnRefreshListener(this);
        pullDownRefresh.setRefreshHeader(header);
        pullDownRefresh.autoRefresh();
        initRecyclerView(recyclerView, pullDownRefresh);
    }

    protected abstract void initRecyclerView(RecyclerView recyclerView, ViewGroup container);

    protected abstract void loadData(boolean loadFirstPage);

    protected abstract String getMyTitle();

    protected void showNormal() {
        empty.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        pullDownRefresh.refreshComplete();
    }

    protected void showEmpty() {
        empty.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}









