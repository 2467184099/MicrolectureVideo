package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment.ChargeFragment1;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment.CourseFragment;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment.FreeFragment1;
import com.example.administrator.microlecturevideo.main.mvp.model.ListBean;
import com.example.administrator.microlecturevideo.main.mvp.presenter.PixelUtils;

import java.util.List;

/**
 * 我的课程视频详情
 */
public class VideoWebActivity1 extends AppCompatActivity implements View.OnClickListener {
    public static VideoView mVideoView;
    private ImageView mImageView_back, mImageView_down, mImageView_magnify, mImageView_shop;
    private TextView mTextView_count, mTextView_money, mTextView_time_current, mTextView_time_total, mTextView_charge, mTextView_Free, mTextView_shop;
    private FrameLayout mFrameLayout;
    public static ImageView mImageView_stop;
    private LinearLayout mLayout_down, mLayout_web, mLayout_video_web, mLinearLayout;
    private RelativeLayout mVideolayout_web;
    private SeekBar mSeekBar;
    public static final int UPDATA_UI = 1;
    private int screen_width, screen_height;
    private boolean isFullScreen = false;
    int position;
    Bundle bundle1;
    Intent intent1;
    int payly;
    String name;
    String clanum;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_web);
        intent1 = this.getIntent();
        bundle1 = intent1.getExtras();
        Intent intent = this.getIntent();
        position = intent.getIntExtra("position", 1);
        List<ListBean> data = CourseFragment.getData();
        String id = data.get(position - 1).getId();
        name = data.get(position - 1).getName();
        payly = data.get(position - 1).getPayly();
        clanum = data.get(position - 1).getClanum();
        price = data.get(position - 1).getPrice();
        Log.e("------", "id=" + id);
        SharedPreferences preferences = this.getSharedPreferences("test1", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("id", id);
        edit.commit();
        //初始化UI布局
        initView();
        //设置视频的播放暂
        setPlayerEvent();
        //播放网络视频
        mVideoView.setVideoURI(null);
        mVideoView.start();
        handler.sendEmptyMessage(UPDATA_UI);
        getFragment();
    }

    private void getFragment() {
        mTextView_charge.setTextColor(Color.BLACK);
        mTextView_charge.setBackgroundColor(Color.GRAY);
        mTextView_Free.setTextColor(Color.GREEN);
        mTextView_Free.setBackgroundColor(Color.WHITE);
        FragmentManager fragmentManager = VideoWebActivity1.this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.feamelayout_web, new FreeFragment1());
        transaction.commit();
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
            super.handleMessage(msg);
            if (msg.what == UPDATA_UI) {
                //获取视频正在播放时间
                int currentPosition = (int) mVideoView.getCurrentPosition();
                //视频播放总时间
                int totalduration = (int) mVideoView.getDuration();
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
                if (mVideoView.isPlaying()) {
                    mImageView_stop.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    //暂停播放
                    mVideoView.pause();
                    handler.removeMessages(UPDATA_UI);

                } else {
                    mImageView_stop.setImageResource(R.drawable.ic_pause_black_24dp);
                    //继续播放
                    mVideoView.start();
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
                mVideoView.seekTo(progress);
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

    //初始化UI布局
    private void initView() {
        PixelUtils.getPix(this);
        mVideoView = (VideoView) findViewById(R.id.videoview_web);
        mImageView_back = (ImageView) findViewById(R.id.img_back_web);
        mImageView_down = (ImageView) findViewById(R.id.img_web_down);
        mImageView_stop = (ImageView) findViewById(R.id.img_web_stop);
        mImageView_magnify = (ImageView) findViewById(R.id.img_web_magnify);
        mTextView_count = (TextView) findViewById(R.id.textView_count_web);
        mTextView_count.setText(clanum);
        mTextView_money = (TextView) findViewById(R.id.web_money);
        mTextView_money.setText(price);
        mTextView_time_current = (TextView) findViewById(R.id.time_current_web);
        mTextView_time_total = (TextView) findViewById(R.id.time_total_tv);
        mTextView_Free = (TextView) findViewById(R.id.free_web);
        mTextView_charge = (TextView) findViewById(R.id.charge_web);
        mFrameLayout = (FrameLayout) findViewById(R.id.feamelayout_web);
        mLayout_down = (LinearLayout) findViewById(R.id.web_down);
        mLayout_web = (LinearLayout) findViewById(R.id.layout_web);
        mSeekBar = (SeekBar) findViewById(R.id.seekbae_web);
        mImageView_shop = (ImageView) findViewById(R.id.videoweb_activity_img);
        mTextView_shop = (TextView) findViewById(R.id.videoweb_activity_txt);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mLayout_video_web = (LinearLayout) findViewById(R.id.laypou_video_web);
        mVideolayout_web = (RelativeLayout) findViewById(R.id.videolayout_web);
        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;
        mImageView_back.setOnClickListener(this);
        mTextView_charge.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        mTextView_charge.setTextColor(Color.GREEN);
                        mTextView_charge.setBackgroundColor(Color.WHITE);
                        mTextView_Free.setTextColor(Color.BLACK);
                        mTextView_Free.setBackgroundColor(Color.GRAY);
                        FragmentManager fragmentManager = VideoWebActivity1.this.getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.feamelayout_web, new ChargeFragment1());
                        transaction.commit();
                        if (payly == 1 || payly == 0) {
                            mTextView_shop.setText("已购买");
                            mImageView_shop.setImageResource(0);
                        } else if (payly == 2) {
                            mTextView_shop.setText("购买");
                            mImageView_shop.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
                        }


                    }
                }
        );
        mTextView_Free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView_charge.setTextColor(Color.BLACK);
                mTextView_charge.setBackgroundColor(Color.GRAY);
                mTextView_Free.setTextColor(Color.GREEN);
                mTextView_Free.setBackgroundColor(Color.WHITE);
                FragmentManager fragmentManager = VideoWebActivity1.this.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.feamelayout_web, new FreeFragment1());
                transaction.commit();
                mTextView_shop.setText("下载");
                mImageView_shop.setImageResource(R.drawable.ic_down);
            }
        });
        mImageView_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VideoWebActivity1.this, DownActivity1.class));
            }
        });
        mLayout_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextView_shop.getText().equals("下载")) {
                    startActivity(new Intent(VideoWebActivity1.this, DownActivity1.class));
                } else if (mTextView_shop.getText().equals("购买")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(VideoWebActivity1.this);
                    dialog.setMessage("是否购买《" + name + "》");
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(VideoWebActivity1.this, "购买成功", Toast.LENGTH_SHORT).show();

                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.create().show();
                }
            }
        });
    }

    //设置屏幕
    private void setVideoViewScale(int width, int height) {
        ViewGroup.LayoutParams params = mVideoView.getLayoutParams();
        params.width = width;
        params.height = height;
        mVideoView.setLayoutParams(params);
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
            mLayout_video_web.setVisibility(View.GONE);
            isFullScreen = true;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //当屏幕方向是竖屏
        else {
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, PixelUtils.dpZpx(240));
            mLayout_video_web.setVisibility(View.VISIBLE);
            isFullScreen = false;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    @Override
    public void onClick(View v) {
        bundle1.putString("ccc", "back");//添加要返回给页面1的数据
        intent1.putExtras(bundle1);
        this.setResult(Activity.RESULT_OK, intent1);//返回页面1
        this.finish();
    }
}
