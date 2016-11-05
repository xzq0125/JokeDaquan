package com.jun.jokedaquan.business.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.fragments.BaseFragment;

import butterknife.ButterKnife;

/**
 * DFragment
 * Created by Tse on 2016/10/29.
 */

public class DFragment extends BaseFragment {

    public static CharSequence getPageTitle() {
        return "DFragment";
    }

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_d;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this, getView());
    }
}
