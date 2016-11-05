package com.jun.jokedaquan.business.picdetails.adapters;

import android.view.ViewGroup;

import com.jun.jokedaquan.base.adapters.RecyclePagerAdapter;
import com.jun.jokedaquan.business.picdetails.viewholders.PhotosViewHolder;
import com.jun.jokedaquan.entity.gif.GifDto;

import java.util.ArrayList;


/**
 * PhotosAdapter
 * Created by Alex on 2016/5/18.
 */
public class PhotosAdapter extends RecyclePagerAdapter<PhotosViewHolder> {

    private ArrayList<GifDto.ContentlistBean> mData;
    private PhotosViewHolder.OnItemClickListener listener;

    public PhotosAdapter(PhotosViewHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotosViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(PhotosViewHolder holder, int position) {
        holder.setPhoto(mData.get(position));
    }

    public void setData(ArrayList<GifDto.ContentlistBean> data) {
        if (mData == data)
            return;
        this.mData = data;
        notifyDataSetChanged();
    }
}
