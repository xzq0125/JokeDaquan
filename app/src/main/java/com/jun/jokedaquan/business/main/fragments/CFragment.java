package com.jun.jokedaquan.business.main.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jun.jokedaquan.base.list.adapter.BaseLoadMoreAdapter;
import com.jun.jokedaquan.base.list.fragment.BaseListFragment;
import com.jun.jokedaquan.business.text.adapters.TextAdapter;
import com.jun.jokedaquan.entity.gif.TextDto;
import com.jun.jokedaquan.request.RequestError;
import com.jun.jokedaquan.request.RequestFactory;
import com.jun.jokedaquan.request.RequestTask;
import com.jun.jokedaquan.utils.ToastUtils;
import com.jun.jokedaquan.widget.stateframelayout.StateFrameLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * CFragment
 * Created by Tse on 2016/10/29.
 */

public class CFragment extends BaseListFragment implements BaseLoadMoreAdapter.OnLoadMoreCallback, RequestTask.OnTaskListener {

    private final CFragment me = this;
    private static final int PAGE_SIZE = 50;
    private int mPage = 1;
    private WeakReference<StateFrameLayout> mLoadMoreView;
    private final TextAdapter mAdapter = new TextAdapter(me);

    public static CharSequence getPageTitle() {
        return "短笑话";
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView, ViewGroup container) {
        recyclerView.setLayoutManager(new LinearLayoutManager(me.getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData(boolean loadFirstPage) {
        mPage = loadFirstPage ? 1 : mPage;
        new RequestTask(this).execute(RequestFactory.getTextJoke(PAGE_SIZE, mPage));
    }

    @Override
    public void onFailed(RequestError error) {
        ToastUtils.showToast(me.getActivity(), error.getMessage());
        if (mPage == 1) {
            showNormal();
        } else {
            try {
                mLoadMoreView.get().error();
            } catch (Exception e) {
                e.printStackTrace();
            }
            showNormal();
        }
    }

    @Override
    public void onSucceed(Object dto, Object tag) {

        showNormal();

        if (dto != null && dto instanceof TextDto) {
            TextDto textDto = (TextDto) dto;
            if (textDto.contentlist == null) {
                textDto.contentlist = new ArrayList<>();
            }

            List<TextDto.ContentlistBean> data = textDto.contentlist;

            boolean hasNext = data.size() > 0;
            if (mPage == 1) {
                if (data.isEmpty())
                    showEmpty();
                mAdapter.setData(data, hasNext);
            } else {
                mAdapter.addData(data, hasNext);
                if (!hasNext)
                    ToastUtils.showToast(me.getContext(), "没有更多了");

            }
            mPage++;
        }
    }

    @Override
    public void onAutoLoadMore(StateFrameLayout loadMore, Object lastData) {
        mLoadMoreView = new WeakReference<>(loadMore);
        loadData(false);
    }

    @Override
    public void onReloadClick(StateFrameLayout loadMore) {
        mLoadMoreView = new WeakReference<>(loadMore);
        loadData(false);
    }

}
