package com.jun.jokedaquan.business.play;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.activitys.BaseActivity;
import com.jun.jokedaquan.utils.SizeUtils;
import com.jun.jokedaquan.utils.StatusBarUtils;

import butterknife.ButterKnife;

public class PlayActivity extends BaseActivity implements OnPreparedListener, View.OnClickListener, Runnable {

    private static final String EXTRA_URL = "extra_url";
    EMVideoView emVideoView;
    private boolean mIsLandscape = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            StatusBarUtils.setLayoutFullscreen(this);
            StatusBarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.transparent));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra(EXTRA_URL);
        emVideoView = (EMVideoView) findViewById(R.id.video_view);
        emVideoView.setOnPreparedListener(this);
        MyVideoControls controls = new MyVideoControls(this);
        emVideoView.setControls(controls);
        controls.addViewOnClickListener(this);

        //For now we just picked an arbitrary item to play.  More can be found at
        //https://archive.org/details/more_animation
        emVideoView.setVideoURI(Uri.parse(url));

        //注册 Settings.System.ACCELEROMETER_ROTATION
        getContentResolver().registerContentObserver(Settings.System.getUriFor(Settings.System.ACCELEROMETER_ROTATION), true, rotationObserver);
    }

    private ContentObserver rotationObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            if (selfChange) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
            }
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏
            //设置全屏即隐藏状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mIsLandscape = true;
            //横屏 视频充满全屏
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) emVideoView.getLayoutParams();
            layoutParams.height = ViewGroup.MarginLayoutParams.MATCH_PARENT;
            layoutParams.width = ViewGroup.MarginLayoutParams.MATCH_PARENT;
            emVideoView.setLayoutParams(layoutParams);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //恢复状态栏
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            mIsLandscape = false;
            //竖屏 视频显示固定大小
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) emVideoView.getLayoutParams();
            layoutParams.width = ViewGroup.MarginLayoutParams.MATCH_PARENT;
            layoutParams.height = SizeUtils.dp2px(this, 208);
            emVideoView.setLayoutParams(layoutParams);
        }

        emVideoView.postDelayed(this, 2000);

    }

    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        emVideoView.start();
    }

    @Override
    public void onBackPressed() {
        if (mIsLandscape) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(rotationObserver);
        emVideoView.release();
    }

    @Override
    public void onClick(View v) {
        //横竖屏切换
        setRequestedOrientation(mIsLandscape ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, PlayActivity.class);
        starter.putExtra(EXTRA_URL, url);
        context.startActivity(starter);
    }

    @Override
    public void run() {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}
