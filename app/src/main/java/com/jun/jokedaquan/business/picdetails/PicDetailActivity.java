package com.jun.jokedaquan.business.picdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.action.BroadcastAction;
import com.jun.jokedaquan.base.activitys.BroadcastActivity;
import com.jun.jokedaquan.business.picdetails.adapters.PhotosAdapter;
import com.jun.jokedaquan.business.picdetails.viewholders.PhotosViewHolder;
import com.jun.jokedaquan.entity.gif.GifDto;
import com.jun.jokedaquan.utils.AnimatorUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicDetailActivity extends BroadcastActivity implements ViewPager.OnPageChangeListener, PhotosViewHolder.OnItemClickListener {

    @Bind(R.id.detail_vp)
    ViewPager vpPics;
    @Bind(R.id.detail_title)
    TextView tvTitle;
    @Bind(R.id.detail_tv_size)
    TextView tvSize;

    private static final String EXTRA_LIST = "list";
    private static final String EXTRA_CURR_ITEM = "curr_item";
    private static final String EXTRA_POS = "pos";
    private ArrayList<GifDto.ContentlistBean> list;
    private int size = 0;
    private int browsePos = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        list = getIntent().getParcelableArrayListExtra(EXTRA_LIST);
        if (list == null || list.isEmpty())
            finish();
        size = list.size();
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        GifDto.ContentlistBean currItem = getIntent().getParcelableExtra(EXTRA_CURR_ITEM);
        if (currItem == null) {
            finish();
            return;
        }

        ButterKnife.bind(this);
        setSupportActionBar(R.id.detail_toolbar);
        PhotosAdapter photosAdapter = new PhotosAdapter(this);
        vpPics.setAdapter(photosAdapter);
        photosAdapter.setData(list);
        int currPos = getCurrentPosition(list, currItem);
        vpPics.setCurrentItem(currPos);
        vpPics.addOnPageChangeListener(this);
        tvTitle.setText(currItem.title);
        tvSize.setText(getSizeString(currPos));
        browsePos = currPos;
        AnimatorUtils.alpha(tvSize);
    }

    private String getSizeString(int currPos) {
        int currPage = currPos + 1;
        return currPage + "/" + size;
    }

    /**
     * 获取当前position
     */
    private int getCurrentPosition(ArrayList<GifDto.ContentlistBean> list,
                                   GifDto.ContentlistBean currItem) {

        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.equals(list.get(i).id, currItem.id)) {
                return i;
            }
        }
        return 0;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        browsePos = position;
        if (position >= 0 && position < list.size()) {
            tvTitle.setText(list.get(position).title);
            tvSize.setText(getSizeString(position));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPhotoViewClick() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        sendLocalBroadcastBeforeFinishActivity();
        super.onBackPressed();
    }

    private void sendLocalBroadcastBeforeFinishActivity() {
        sendLocalBroadcast(new Intent(BroadcastAction.ACTION_SCROLL_PICLIST).putExtra(EXTRA_POS, browsePos));
        finish();
    }

    public static int getBrowsePos(Intent intent) {
        return intent.getIntExtra(EXTRA_POS, -1);
    }

    /**
     * 启动PicDetailActivity
     *
     * @param context  Context
     * @param list     数据列表
     * @param currItem 当前item
     */
    public static void start(Context context, ArrayList<? extends Parcelable> list, GifDto.ContentlistBean currItem) {
        Intent starter = new Intent(context, PicDetailActivity.class);
        starter.putParcelableArrayListExtra(EXTRA_LIST, list);
        starter.putExtra(EXTRA_CURR_ITEM, currItem);
        context.startActivity(starter);
    }

}
