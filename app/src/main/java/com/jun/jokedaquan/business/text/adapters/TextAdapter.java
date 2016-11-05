package com.jun.jokedaquan.business.text.adapters;

import android.view.View;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.list.adapter.BaseLoadMoreAdapter;
import com.jun.jokedaquan.business.text.viewholders.TextViewHolder;
import com.jun.jokedaquan.entity.gif.TextDto;

/**
 * TextAdapter
 * Created by Tse on 2016/11/5.
 */

public class TextAdapter extends BaseLoadMoreAdapter<TextDto.ContentlistBean, TextViewHolder> {

    public TextAdapter(OnLoadMoreCallback loadMoreCallback) {
        super(loadMoreCallback);
    }

    @Override
    public TextViewHolder onCreateNormalViewHolder(View itemView) {
        return new TextViewHolder(itemView);
    }

    @Override
    public void onConvert(TextViewHolder holder, int position) {
        holder.setData(getDataAt(position));
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_text;
    }

}
