package com.jun.jokedaquan.business.pic.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.list.viewholder.BaseLoadMoreViewHolder;
import com.jun.jokedaquan.entity.gif.GifDto;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * PicViewHolder
 * Created by Tse on 2016/11/5.
 */

public class PicViewHolder extends BaseLoadMoreViewHolder {

    @Bind(R.id.item_iv)
    ImageView ivPic;

    @Bind(R.id.item_tv_title)
    TextView tvTitle;

    @Bind(R.id.item_tv_ct)
    TextView tvCt;

    public PicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(GifDto.ContentlistBean data) {

        itemView.setTag(data);

        Glide.with(ivPic.getContext())
                .load(data.img)
                .placeholder(R.color.lineColor_toolbar)
                .crossFade()
                .fitCenter()
                .into(ivPic);

        tvTitle.setText(data.title);

        String ct = data.ct;
        if (ct != null && ct.contains(".")) {
            String[] s = ct.split("\\.");
            ct = s[0];
        }
        tvCt.setText("－" + ct);
        tvCt.setText(ct);
    }
}
