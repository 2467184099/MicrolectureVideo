package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.presenter.PixelUtils;


/**
 * @author
 * 视频详解
 */

public class VideoActivity1 extends AppCompatActivity {
    String viewUrl;
    VideoView videoView;
    public static final int UPDATA_UI = 1;
    private ImageView mImageView_back, mImageView_stop, mImageView_magnify;
    private boolean isFullScreen = false;
    private TextView mTextView_time_current, mTextView_time_total;
    private SeekBar mSeekBar;
    private RelativeLayout mVideolayout_web;
    private LinearLayout linearLayout_biaoti;
    private int screen_width, screen_height;
    /**
     * the life of on create
     * @deprecated
     * @param savedInstanceState saved ins
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoactivity);
        Intent intent = this.getIntent();
        viewUrl = intent.getStringExtra("file");
        //初始化UI布局
        initView();
        //设置视频的播放暂
        setPlayerEvent();
        videoView.setVideoPath(Environment.getExternalStorageDirectory().getPath() + "/91taoke/" + viewUrl);
        videoView.start();
        mImageView_stop.setImageResource(R.drawable.ic_pause_black_24dp);
        handler.sendEmptyMessage(UPDATA_UI);


    }

    private void initView() {
        PixelUtils.getPix(this);
        videoView = (VideoView) findViewById(R.id.videoactivity_videoview);
        mImageView_back = (ImageView) findViewById(R.id.img_back_videoavtivity);
        mImageView_stop = (ImageView) findViewById(R.id.img_videoactivity_stop);
        mImageView_magnify = (ImageView) findViewById(R.id.img_videoactivity_magnify);
        mTextView_time_current = (TextView) findViewById(R.id.time_current_videoactivity);
        mTextView_time_total = (TextView) findViewById(R.id.time_total_tv_videoactivity);
        mSeekBar = (SeekBar) findViewById(R.id.seekbae_videoactivity);
        linearLayout_biaoti= (LinearLayout) findViewById(R.id.laypou_video_web);
        mVideolayout_web = (RelativeLayout) findViewById(R.id.videolayout_videoactivity);
        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;
        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //时间的格式化
    private void updataTextViewTimeFormat(TextView textView, int millisecond) {
        int second = millisecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String str = null;
        if (hh != 0) {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }
        textView.setText(str);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = 0;
            int totalduration = 0;
            super.handleMessage(msg);
            if (msg.what == UPDATA_UI) {
                //获取视频正在播放时间
                currentPosition = (int) videoView.getCurrentPosition();
                //视频播放总时间
                totalduration = (int) videoView.getDuration();
                //格式化视频播放时间
                updataTextViewTimeFormat(mTextView_time_current, currentPosition);
                updataTextViewTimeFormat(mTextView_time_total, totalduration);
                mSeekBar.setMax(totalduration);
                mSeekBar.setProgress(currentPosition);
                handler.sendEmptyMessageDelayed(UPDATA_UI, 500);
            }
        }

    };

    //设置事件
    private void setPlayerEvent() {
        /**
         * 视频的播放暂停事件
         */
        mImageView_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    mImageView_stop.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    //暂停播放
                    videoView.pause();
                    handler.removeMessages(UPDATA_UI);
                } else {
                    mImageView_stop.setImageResource(R.drawable.ic_pause_black_24dp);
                    //继续播放
                    videoView.start();
                    handler.sendEmptyMessage(UPDATA_UI);
                }

            }
        });
        /**
         * 播放进度事件
         */
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updataTextViewTimeFormat(mTextView_time_current, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeMessages(UPDATA_UI);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                //领视频的进度遵循seekbar停止拖动的这一刻进度
                videoView.seekTo(progress);
                handler.sendEmptyMessage(UPDATA_UI);
            }
        });
        /**
         * 横竖屏的切换事件
         */
        mImageView_magnify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFullScreen) {
                    //切换竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    //切换横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(UPDATA_UI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //计算屏幕大小
    private void setVideoViewScale(int width, int height) {
        ViewGroup.LayoutParams params = videoView.getLayoutParams();
        params.width = width;
        params.height = height;
        videoView.setLayoutParams(params);
        ViewGroup.LayoutParams layoutParams = mVideolayout_web.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mVideolayout_web.setLayoutParams(layoutParams);
    }

    /**
     * 监听到屏幕方向改变
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //当屏幕方向为横屏
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayout_biaoti.setVisibility(View.GONE);
            isFullScreen = true;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //当屏幕方向是竖屏
        else {
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, PixelUtils.dpZpx(240));
            linearLayout_biaoti.setVisibility(View.VISIBLE);
            isFullScreen = false;
           /* getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);*/
        }
    }
}
