package com.jun.jokedaquan.business.text.viewholders;

import android.view.View;
import android.widget.TextView;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.list.viewholder.BaseLoadMoreViewHolder;
import com.jun.jokedaquan.entity.gif.TextDto;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * PicViewHolder
 * Created by Tse on 2016/11/5.
 */

public class TextViewHolder extends BaseLoadMoreViewHolder {

    @Bind(R.id.item_tv_title)
    TextView tvTitle;

    @Bind(R.id.item_tv_content)
    TextView tvContent;

    @Bind(R.id.item_tv_ct)
    TextView tvCt;

    public TextViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(TextDto.ContentlistBean data) {
        tvTitle.setText(data.title);

        String text = data.text;
        if (text != null) {
            text = text.replaceAll("<p>", "");
            text = text.replaceAll("</p>", "");
        }
        tvContent.setText(text);

        String ct = data.ct;
        if (ct != null && ct.contains(".")) {
            String[] s = ct.split("\\.");
            ct = s[0];
        }
        tvCt.setText("－" + ct);
    }
}
