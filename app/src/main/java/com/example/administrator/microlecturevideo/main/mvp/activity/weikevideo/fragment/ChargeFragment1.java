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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.VideoWebActivity1;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.FreeAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener1;
import com.example.administrator.microlecturevideo.main.mvp.model.ChargeInfo;
import com.example.administrator.microlecturevideo.main.mvp.model.FreeInfo;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * 视频详情 收费列表
 */

public class ChargeFragment1 extends Fragment implements XListView.IXListViewListener, OnLoadRegisterListener, AdapterView.OnItemClickListener, OnLoadRegisterListener1 {
    XListView mXlist;
    String url;
    Handler mHandler;
    String id1;
    List<FreeInfo.DataBean.ListBean> list;
    int totalPage;
    RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chargefragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        mXlist = (XListView) view.findViewById(R.id.list_charge);
        mXlist.setOnItemClickListener(this);
        SharedPreferences shar = getActivity().getSharedPreferences("test1", Context.MODE_PRIVATE);
        id1 = shar.getString("id", null);
        Log.e("------", "id=" + id1);
        url = VideoURL.SHOUFEI + "subjectId=" + id1 + "&p=" + 1;
        queue = Volley.newRequestQueue(getActivity());
        VolleyUtils.connectionGET(url, this, queue);
    }

   //收费视频列表数据处理
    @Override
    public void getRegister(String message) {
        Gson gson = new Gson();
        FreeInfo freeInfo = gson.fromJson(message, FreeInfo.class);
        FreeInfo.DataBean data = freeInfo.getData();
        int code = freeInfo.getCode();
        if (code == 0) {
            totalPage = data.getTotalPage();
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
                list.clear();
                String url = VideoURL.SHOUFEI + "subjectId=" + id1 + "&p=" + 1;
                VolleyUtils.connectionGET(url, ChargeFragment1.this, queue);
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
                String url = VideoURL.SHOUFEI + "subjectId=" + id1 + "&p=" + totalPage;

                VolleyUtils.connectionGET(url, ChargeFragment1.this, queue);
                if (totalPage == totalPage) {
                    Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
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


   //点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String id2 = list.get(position-1).getId();
        Log.e("123","id2="+id2);
        String url1 = VideoURL.BOFANG + "uid=" + 11945763 + "&subjectId=" + id1 + "&subId=" + id2;
        VolleyUtils.connectionGET1(url1, this, queue);
    }
    String s;
    String replace;
  //视频播放数据处理
    @Override
    public void getRegister1(String message) {
        Log.e("-----", message.toString());
        Gson gson = new Gson();
        ChargeInfo chargeInfo = gson.fromJson(message, ChargeInfo.class);
        int code = chargeInfo.getCode();
        if (code == 0) {
            ArrayList<String> data = chargeInfo.getData();
            for (int i = 0; i < data.size(); i++) {
                s = data.get(i);
            }
            VideoWebActivity1.mVideoView.stopPlayback();
            replace = s.replace(".flv", ".mp4");
            VideoWebActivity1.mVideoView.setVideoURI(Uri.parse(replace));
            VideoWebActivity1.mVideoView.start();
            VideoWebActivity1.mImageView_stop.setImageResource(R.drawable.ic_pause_black_24dp);
        } else if (code == 1) {
            Toast.makeText(getActivity(), "尚未购买", Toast.LENGTH_SHORT).show();
        }
    }
}

