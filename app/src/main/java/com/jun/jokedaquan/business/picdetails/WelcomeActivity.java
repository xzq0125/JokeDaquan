package com.jun.jokedaquan.business.picdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.activitys.BaseActivity;
import com.jun.jokedaquan.business.main.MainActivity;


public class WelcomeActivity extends BaseActivity implements Runnable {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        getWindow().getDecorView().postDelayed(this, 1500);
    }

    @Override
    public void run() {

        if (isFinishing())
            return;

        MainActivity.start(this);

        finish();

    }
}
