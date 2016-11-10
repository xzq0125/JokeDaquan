package com.jun.jokedaquan.business.sister.adapters;

import android.view.View;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.list.adapter.BaseLoadMoreAdapter;
import com.jun.jokedaquan.business.sister.viewholders.SisterViewHolder;
import com.jun.jokedaquan.entity.sister.SisterDto;

/**
 * SisterAdapter
 * Created by sean on 2016/11/10.
 */

public class SisterAdapter extends BaseLoadMoreAdapter<SisterDto.SisterContentDto, SisterViewHolder> {

    public SisterAdapter(OnLoadMoreCallback loadMoreCallback) {
        super(loadMoreCallback);
    }

    @Override
    public SisterViewHolder onCreateNormalViewHolder(View itemView) {
        return new SisterViewHolder(itemView);
    }

    @Override
    public void onConvert(SisterViewHolder holder, int position) {
        holder.setData(getDataAt(position));
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_sister;
    }
}
