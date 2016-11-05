package com.jun.jokedaquan.business.pic.adapters;

import android.view.View;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.list.adapter.BaseLoadMoreAdapter;
import com.jun.jokedaquan.business.pic.viewholders.PicViewHolder;
import com.jun.jokedaquan.entity.gif.GifDto;

import java.util.List;

/**
 * PicAdapter
 * Created by Tse on 2016/11/5.
 */

public class PicAdapter extends BaseLoadMoreAdapter<GifDto.ContentlistBean, PicViewHolder> {

    public PicAdapter(OnLoadMoreCallback loadMoreCallback) {
        super(loadMoreCallback);
    }

    @Override
    public PicViewHolder onCreateNormalViewHolder(View itemView) {
        return new PicViewHolder(itemView);
    }

    @Override
    public void onConvert(PicViewHolder holder, int position) {
        holder.setData(getDataAt(position));
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_pic;
    }

    public List<GifDto.ContentlistBean> getData() {
        return mData;
    }
}
