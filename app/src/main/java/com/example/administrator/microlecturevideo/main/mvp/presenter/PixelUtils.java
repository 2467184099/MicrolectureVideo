package com.example.administrator.microlecturevideo.main.mvp.presenter;

import android.content.Context;

/**
 * Created by Administrator on 2017/5/2.
 */

public class PixelUtils {
    public static Context mContext;

    public static void getPix(Context context) {
        mContext = context;
    }

    public static int dpZpx(float value) {
        final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }
}
