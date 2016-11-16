package com.jun.jokedaquan.business.play;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.jun.jokedaquan.R;
import com.jun.jokedaquan.base.activitys.BaseActivity;
import com.jun.jokedaquan.utils.StatusBarUtils;

import butterknife.ButterKnife;

public class PlayActivity extends BaseActivity implements OnPreparedListener {

    private static final String EXTRA_URL = "extra_url";
    EMVideoView emVideoView;

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
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra(EXTRA_URL);
        emVideoView = (EMVideoView) findViewById(R.id.video_view);
        emVideoView.setOnPreparedListener(this);
        emVideoView.setControls(new MyVideoControls(this));

        //For now we just picked an arbitrary item to play.  More can be found at
        //https://archive.org/details/more_animation
        emVideoView.setVideoURI(Uri.parse(url));

    }

    @Override
    public void onPrepared() {
        //Starts the video playback as soon as it is ready
        emVideoView.start();
    }

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, PlayActivity.class);
        starter.putExtra(EXTRA_URL, url);
        context.startActivity(starter);
    }

}
