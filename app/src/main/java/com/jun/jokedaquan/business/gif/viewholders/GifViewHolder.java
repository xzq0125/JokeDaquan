package com.jun.jokedaquan.business.gif.viewholders;

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

public class GifViewHolder extends BaseLoadMoreViewHolder implements View.OnClickListener {

    @Bind(R.id.item_iv)
    ImageView ivPic;

    @Bind(R.id.item_iv_gif)
    ImageView ivGif;

    @Bind(R.id.item_iv_play_gif)
    ImageView ivPlayGif;

    @Bind(R.id.item_tv_title)
    TextView tvTitle;

    @Bind(R.id.item_tv_ct)
    TextView tvCt;

    public GifViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ivPlayGif.setOnClickListener(this);
    }

    private GifDto.ContentlistBean data;

    public void setData(GifDto.ContentlistBean data) {
        this.data = data;

        Glide.with(ivPic.getContext())
                .load(data.img)
                .asBitmap()
                .placeholder(R.color.lineColor_toolbar)
                .fitCenter()
                .into(ivPic);

        ivPlayGif.setVisibility(data.isPlay ? View.GONE : View.VISIBLE);
        ivGif.setVisibility(data.isPlay ? View.VISIBLE : View.GONE);

        String title = data.title;
        if (title != null) {
            title = title.replaceAll("<b>", "");
        }
        tvTitle.setText(title);

        String ct = data.ct;
        if (ct != null && ct.contains(".")) {
            String[] s = ct.split("\\.");
            ct = s[0];
        }
        tvCt.setText("－" + ct);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.item_iv_play_gif:
                if (data == null)
                    return;

                data.isPlay = true;

                ivGif.setImageBitmap(null);
                ivGif.setVisibility(View.VISIBLE);
                ivPlayGif.setVisibility(View.GONE);
                Glide.with(ivGif.getContext())
                        .load(data.img)
                        .into(ivGif);
                break;

            default:
                break;
        }
    }
}
