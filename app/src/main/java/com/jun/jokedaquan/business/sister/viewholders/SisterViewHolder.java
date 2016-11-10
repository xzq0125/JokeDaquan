package com.jun.jokedaquan.business.sister.viewholders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.list.viewholder.BaseLoadMoreViewHolder;
import com.jun.jokedaquan.entity.sister.SisterDto;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * SisterViewHolder
 * Created by sean on 2016/11/10.
 */

public class SisterViewHolder extends BaseLoadMoreViewHolder {

    @Bind(R.id.item_sister_head)
    ImageView ivHead;

    @Bind(R.id.item_sister_name)
    TextView tvName;
    @Bind(R.id.item_sister_time)
    TextView tvTime;
    @Bind(R.id.item_sister_text)
    TextView tvText;

    @Bind(R.id.item_sister_img0)
    ImageView ivImg0;
    @Bind(R.id.item_sister_img1)
    ImageView ivImg1;
    @Bind(R.id.item_sister_img2)
    ImageView ivImg2;
    @Bind(R.id.item_sister_img3)
    ImageView ivImg3;

    @Bind(R.id.item_sister_love)
    TextView tvLove;
    @Bind(R.id.item_sister_hate)
    TextView tvHate;
    @Bind(R.id.item_sister_share)
    TextView tvShare;
    @Bind(R.id.item_sister_msg)
    TextView tvMsg;

    @Bind(R.id.item_sister_observer)
    TextView tvObserver;
    @Bind(R.id.item_sister_comment)
    TextView tvComment;

    private Context context;

    public SisterViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void setData(SisterDto.SisterContentDto data) {
        String text = data.text;
        if (text != null) {
            text = text.replaceAll("\\n", "").trim();
        }

        Glide.with(context).load(data.profile_image).into(ivHead);
        tvName.setText(data.name);
        tvTime.setText(data.create_time);
        tvText.setText(text);

        if (TextUtils.equals(data.image0, data.image1)) {
            loadImg(data.image0, ivImg0);
        } else {
            loadImg(data.image0, ivImg0);
            loadImg(data.image1, ivImg1);
            loadImg(data.image2, ivImg2);
            loadImg(data.image3, ivImg3);
        }

        tvLove.setText(data.love);
        tvHate.setText(data.hate);
        tvShare.setText(data.love);
        tvMsg.setText(data.hate);

        tvObserver.setText(data.name + "：");
        tvComment.setText(text);
    }

    private void loadImg(String url, ImageView imageView) {
        if (url == null)
            return;
        if (url.endsWith(".gif"))
            Glide.with(context).load(url).into(imageView);
        else
            Glide.with(context).load(url).centerCrop().into(imageView);
    }

}









