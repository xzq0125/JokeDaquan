package com.jun.jokedaquan.base.list.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.fragments.BroadcastFragment;
import com.jun.jokedaquan.utils.RecyclerViewUtils;
import com.jun.jokedaquan.widget.pulldownrefresh.RefreshLayout;
import com.jun.jokedaquan.widget.pulldownrefresh.RefreshLayoutHeader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * BaseListFragment
 * Created by Tse on 2016/10/31.
 */

public abstract class BaseListFragment extends BroadcastFragment
        implements RefreshLayout.OnRefreshListener {

    @Bind(R.id.rl)
    RefreshLayout pullDownRefresh;

    @Bind(R.id.iv_back_top)
    ImageView ivBackTop;

    @Bind(android.R.id.empty)
    ImageView empty;

    @Bind(android.R.id.list)
    RecyclerView recyclerView;
    private BackTopManager backTopManager;


    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this, getView());
        RefreshLayoutHeader header = new RefreshLayoutHeader(getActivity());
        pullDownRefresh.addOnRefreshListener(header);
        pullDownRefresh.addOnRefreshListener(this);
        pullDownRefresh.setRefreshHeader(header);
        pullDownRefresh.autoRefresh();
        initRecyclerView(recyclerView, pullDownRefresh);
        recyclerView.addOnScrollListener(onScrollListener);
        backTopManager = new BackTopManager(ivBackTop);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() > 30) {
                if (newState != 0)
                    backTopManager.visible();
            } else {
                backTopManager.gone();
            }
        }
    };

    protected abstract void initRecyclerView(RecyclerView recyclerView, ViewGroup container);

    protected abstract void loadData(boolean loadFirstPage);

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

    @OnClick(R.id.iv_back_top)
    public void onClick(View view) {
        backTopManager.gone();
        RecyclerViewUtils.scrollToTopWithAnimation(recyclerView);
        //pullDownRefresh.autoRefresh();
    }
}
