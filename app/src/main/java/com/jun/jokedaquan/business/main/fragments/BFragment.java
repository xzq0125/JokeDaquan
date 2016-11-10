package com.jun.jokedaquan.business.main.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.action.BroadcastAction;
import com.jun.jokedaquan.base.list.adapter.BaseLoadMoreAdapter;
import com.jun.jokedaquan.base.list.fragment.BaseListFragment;
import com.jun.jokedaquan.business.pic.adapters.PicAdapter;
import com.jun.jokedaquan.business.picdetails.PicDetailActivity;
import com.jun.jokedaquan.entity.gif.GifDto;
import com.jun.jokedaquan.request.RequestError;
import com.jun.jokedaquan.request.RequestFactory;
import com.jun.jokedaquan.request.RequestTask;
import com.jun.jokedaquan.utils.RecyclerViewUtils;
import com.jun.jokedaquan.utils.ToastUtils;
import com.jun.jokedaquan.widget.divider.DividerItemDecoration;
import com.jun.jokedaquan.widget.stateframelayout.StateFrameLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * BFragment
 * Created by Tse on 2016/10/29.
 */

public class BFragment extends BaseListFragment implements
        BaseLoadMoreAdapter.OnLoadMoreCallback,
        RequestTask.OnTaskListener,
        BaseLoadMoreAdapter.OnItemClickListener {

    private final BFragment me = this;
    private static final int PAGE_SIZE = 20;
    private int mPage = 1;
    private WeakReference<StateFrameLayout> mLoadMoreView;
    private final PicAdapter mAdapter = new PicAdapter(me);
    private RecyclerView recyclerView;
    private boolean scrollListWhenResume = false;
    private int prevPos = 0;
    private int toPos = 0;

    public static CharSequence getPageTitle() {
        return "趣图";
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView, ViewGroup container) {
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(me.getContext()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(me.getContext(), R.drawable.divider_sister_list)));
        mAdapter.addOnItemClickListener(me);
    }

    @Override
    protected void loadData(boolean loadFirstPage) {
        mPage = loadFirstPage ? 1 : mPage;
        new RequestTask(this).execute(RequestFactory.getPicJoke(PAGE_SIZE, mPage));
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

        if (dto != null && dto instanceof GifDto) {
            GifDto gifDto = (GifDto) dto;
            if (gifDto.contentlist == null) {
                gifDto.contentlist = new ArrayList<>();
            }

            List<GifDto.ContentlistBean> data = gifDto.contentlist;

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

    @Override
    public void onItemClick(Object dto, int position) {
        prevPos = position;
        List<GifDto.ContentlistBean> list = mAdapter.getData();
        if (dto != null && dto instanceof GifDto.ContentlistBean) {
            GifDto.ContentlistBean currItem = (GifDto.ContentlistBean) dto;
            PicDetailActivity.start(me.getActivity(), (ArrayList<? extends Parcelable>) list, currItem);
        }
    }

    @Override
    protected void onAddAction(IntentFilter filter) {
        super.onAddAction(filter);
        filter.addAction(BroadcastAction.ACTION_SCROLL_PICLIST);
    }

    @Override
    protected void onLocalBroadcastReceive(Context context, Intent intent) {
        super.onLocalBroadcastReceive(context, intent);
        final String action = intent.getAction();

        switch (action) {
            case BroadcastAction.ACTION_SCROLL_PICLIST:
                toPos = PicDetailActivity.getBrowsePos(intent);
                if (isResume())
                    smoothScrollToPosition();
                else
                    scrollListWhenResume = true;
                break;

            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (scrollListWhenResume) {
            scrollListWhenResume = false;
            smoothScrollToPosition();
        }
    }

    private void smoothScrollToPosition() {
        if (prevPos != toPos && toPos >= 0)
            RecyclerViewUtils.scrollToPositionWithAnimation(recyclerView, toPos);
    }

}
