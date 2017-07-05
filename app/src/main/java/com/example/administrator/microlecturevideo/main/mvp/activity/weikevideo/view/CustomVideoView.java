package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2017/5/3.
 */

public class CustomVideoView extends android.widget.VideoView {
    int defaultWidth=1920;
    int defaultHeight=1080;
    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getDefaultSize(defaultWidth,widthMeasureSpec);
        int height=getDefaultSize(defaultHeight,heightMeasureSpec);
        Log.e("111111","width="+width+"height="+height);
        setMeasuredDimension(width,height);
    }
}
