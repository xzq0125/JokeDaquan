package com.jun.jokedaquan.business.gif.adapters;

import android.view.View;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.list.adapter.BaseLoadMoreAdapter;
import com.jun.jokedaquan.business.gif.viewholders.GifViewHolder;
import com.jun.jokedaquan.entity.gif.GifDto;

/**
 * PicAdapter
 * Created by Tse on 2016/11/5.
 */

public class GifAdapter extends BaseLoadMoreAdapter<GifDto.ContentlistBean, GifViewHolder> {

    public GifAdapter(OnLoadMoreCallback loadMoreCallback) {
        super(loadMoreCallback);
    }

    @Override
    public GifViewHolder onCreateNormalViewHolder(View itemView) {
        return new GifViewHolder(itemView);
    }

    @Override
    public void onConvert(GifViewHolder holder, int position) {
        holder.setData(getDataAt(position));
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_pic;
    }

}
