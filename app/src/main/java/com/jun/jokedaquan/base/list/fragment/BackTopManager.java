package com.jun.jokedaquan.base.list.fragment;

import android.support.annotation.NonNull;
import android.view.View;

import com.jun.jokedaquan.utils.AnimatorUtils;


/**
 * BackTopManager
 * Created by Tse on 2016/10/20.
 */

public class BackTopManager implements Runnable {

    private static final int DELAY = 1000;

    private View view;
    private boolean visible;

    public BackTopManager(@NonNull View view) {
        this.view = view;
        view.setAlpha(0);
    }

    public void visible() {

        if (visible)
            return;

        AnimatorUtils.alpha(view, 0, 1, DELAY);

        view.postDelayed(this, DELAY);

        visible = true;
    }

    public void gone() {

        if (!visible)
            return;

        AnimatorUtils.alpha(view, 1, 0, DELAY);

        view.postDelayed(this, DELAY);

        visible = false;
    }

    @Override
    public void run() {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}






