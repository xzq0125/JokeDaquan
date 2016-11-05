package com.jun.jokedaquan.business.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.activitys.BaseActivity;
import com.jun.jokedaquan.business.main.adapters.MainFragmentPagerAdapter;
import com.jun.jokedaquan.utils.ToastUtils;
import com.jun.jokedaquan.widget.smarttablayout.SmartTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_stl_tabs)
    SmartTabLayout tabs;

    @Bind(R.id.main_vp_fragments)
    ViewPager vpFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        MainFragmentPagerAdapter pagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        vpFragments.setAdapter(pagerAdapter);
        vpFragments.setOffscreenPageLimit(pagerAdapter.getCount());
        tabs.setViewPager(vpFragments);
    }

    @Override
    public void onBackPressed() {
        if (canFinish)
            super.onBackPressed();
        else {
            canFinish = true;
            ToastUtils.showToast(this, "再按一次退出~");
            vpFragments.postDelayed(mCancelFinishTask, 2000);
        }
    }

    private boolean canFinish = false;
    private final Runnable mCancelFinishTask = new Runnable() {
        @Override
        public void run() {
            canFinish = false;
        }
    };
}
