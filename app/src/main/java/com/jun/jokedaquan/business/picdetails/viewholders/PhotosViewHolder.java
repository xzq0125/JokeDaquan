package com.jun.jokedaquan.business.picdetails.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.adapters.RecyclePagerAdapter;
import com.jun.jokedaquan.entity.gif.GifDto;


/**
 * PhotosViewHolder
 * Created by Alex on 2016/5/18.
 */
public class PhotosViewHolder extends RecyclePagerAdapter.PagerViewHolder implements View.OnClickListener {

    private PhotoView photo;
    private OnItemClickListener listener;

    public PhotosViewHolder(ViewGroup parent, OnItemClickListener listener) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_common_photos, parent, false));
        this.listener = listener;
        photo = (PhotoView) itemView;
        photo.enable();
        photo.setOnClickListener(this);
    }

    public void setPhoto(GifDto.ContentlistBean data) {
        photo.setImageDrawable(null);
        Glide.with(photo.getContext())
                .load(data.img)
                .placeholder(R.color.lineColor_toolbar)
                .crossFade()
                .fitCenter()
                .into(photo);
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onPhotoViewClick();
    }

    public interface OnItemClickListener {
        void onPhotoViewClick();
    }

}
