package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment.CourseFragment;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment.DownLoadFragment;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment.VideoFragment;

/**
 * 程序主入口
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImg_1, mImg_2, mImg_3;
    public static TextView mText_1, mText_2, mText_3;
    //碎片的区域布局
    private FrameLayout mFrameLayout;
    //控制碎片替换的按钮区域
    private LinearLayout mLayout_video,
            mLayout_content,
            mLayout_download;
    public static LinearLayout linearLayout_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        // 第一次启动时选中第0个tab
        setTabSelection(0);

    }


    /**
     * 初始化控件
     */
    private void initView() {
        mLayout_video = (LinearLayout) findViewById(R.id.layout_myvideo);
        mLayout_content = (LinearLayout) findViewById(R.id.layout_course);
        mLayout_download = (LinearLayout) findViewById(R.id.layout_download);
        linearLayout_main= (LinearLayout) findViewById(R.id.mainactivity_layout);
        mImg_1 = (ImageView) findViewById(R.id.img_1);
        mImg_2 = (ImageView) findViewById(R.id.img_2);
        mImg_3 = (ImageView) findViewById(R.id.img_3);
        mText_1 = (TextView) findViewById(R.id.txt_1);
        mText_2 = (TextView) findViewById(R.id.txt_2);
        mText_3 = (TextView) findViewById(R.id.txt_3);

        //设置监听事件
        monitort();
    }

    /**
     * 设置监听事件
     */
    private void monitort() {
        mLayout_video.setOnClickListener(this);
        mLayout_content.setOnClickListener(this);
        mLayout_download.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.layout_myvideo:
                // 当点击了微课视频tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.layout_course:
                // 当点击了我的课程tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.layout_download:
                // 当点击了我的下载tab时，选中第3个tab
                setTabSelection(2);
                break;


        }

    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示微课视频，1表示我的课程，2表示我的下载
     */
    public void setTabSelection(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (index) {
            case 0:
                mText_1.setTextColor(Color.GREEN);
                mImg_1.setBackgroundColor(Color.GREEN);
                mText_2.setTextColor(Color.GRAY);
                mImg_2.setBackgroundColor(Color.GRAY);
                mText_3.setTextColor(Color.GRAY);
                mImg_3.setBackgroundColor(Color.GRAY);
                transaction.replace(R.id.feamelayout, new VideoFragment());
                transaction.commit();
                break;
            case 1:
                mText_2.setTextColor(Color.GREEN);
                mImg_2.setBackgroundColor(Color.GREEN);
                mText_1.setTextColor(Color.GRAY);
                mImg_1.setBackgroundColor(Color.GRAY);
                mText_3.setTextColor(Color.GRAY);
                mImg_3.setBackgroundColor(Color.GRAY);
                transaction.replace(R.id.feamelayout, new CourseFragment());
                transaction.commit();
                break;
            case 2:
                mText_2.setTextColor(Color.GRAY);
                mImg_2.setBackgroundColor(Color.GRAY);
                mText_1.setTextColor(Color.GRAY);
                mImg_1.setBackgroundColor(Color.GRAY);
                mText_3.setTextColor(Color.GREEN);
                mImg_3.setBackgroundColor(Color.GREEN);
                transaction.replace(R.id.feamelayout, new DownLoadFragment());
                transaction.commit();
                break;


        }


    }


}
