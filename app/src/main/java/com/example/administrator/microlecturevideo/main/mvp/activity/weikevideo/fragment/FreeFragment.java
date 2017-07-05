package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.VideoWebActivity;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.FreeAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.model.FreeInfo;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * 视频详情 免费列表
 */

public class FreeFragment extends Fragment implements XListView.IXListViewListener, OnLoadRegisterListener, AdapterView.OnItemClickListener {
    XListView mXlist;
    String url;
    Handler mHandler;
    List<FreeInfo.DataBean.ListBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.freefragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        mXlist = (XListView) view.findViewById(R.id.list_free);
        mXlist.setOnItemClickListener(this);
        SharedPreferences shar = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);
        String id = shar.getString("id", null);
        url = VideoURL.MIANFEI + "subjectId=" + id + "&p=" + 1;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        VolleyUtils.connectionGET(url, this, queue);

    }


    //免费列表数据处理
    @Override
    public void getRegister(String message) {
        Gson gson = new Gson();
        FreeInfo freeInfo = gson.fromJson(message, FreeInfo.class);
        FreeInfo.DataBean data = freeInfo.getData();
        int code = freeInfo.getCode();
        if (code == 0) {
            list = data.getList();
            FreeAdapter freeAdapter = new FreeAdapter(getActivity());
            freeAdapter.getTest(list);
            mXlist.setAdapter(freeAdapter);
            freeAdapter.notifyDataSetChanged();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setMessage("暂无数据，请重新加载");
            dialog.setTitle("提示");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
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

        //设置两个方法
        //上拉加载
        mXlist.setPullLoadEnable(true);
        //下拉刷新
        mXlist.setPullRefreshEnable(true);
        //上拉刷新和下拉加载都必须设置监听事件
        mXlist.setXListViewListener(this);

    }

    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, 1000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
                Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    //停止加载和刷新
    public void stop() {
        //停止加载和刷新
        mXlist.stopLoadMore();
        mXlist.stopRefresh();
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mXlist.setRefreshTime(dateFormat.format(new Date(System.currentTimeMillis())));
    }

    String s;

    //点击事件及视频播放
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VideoWebActivity.mVideoView.stopPlayback();
        List<String> urlArr = list.get(position - 1).getUrlArr();
        for (int i = 0; i < urlArr.size(); i++) {
            s = urlArr.get(i);
        }
        String replace = s.replace(".flv", ".mp4");
        VideoWebActivity.mVideoView.setVideoURI(Uri.parse(replace));
        VideoWebActivity.mVideoView.start();
        VideoWebActivity.mImageView_stop.setImageResource(R.drawable.ic_pause_black_24dp);

    }


}
