package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.ExerciseWebActivity;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.MicroletureAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.model.DataBean;
import com.example.administrator.microlecturevideo.main.mvp.model.ListBean;
import com.example.administrator.microlecturevideo.main.mvp.model.Obj;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * 习题微课
 */

public class ExerciseFragment extends Fragment implements AdapterView.OnItemClickListener, XListView.IXListViewListener, OnLoadRegisterListener {
    XListView mXlist;

    Handler mHandler;
    String url;
    String url1;
    RequestQueue queue;
    MicroletureAdapter mAdapter;
    static List<ListBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.exercise, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mXlist = (XListView) view.findViewById(R.id.list_exercise);
        mXlist.setOnItemClickListener(this);
        mHandler = new Handler();
        SharedPreferences shar = getActivity().getSharedPreferences("123", Context.MODE_PRIVATE);
        String id4 = shar.getString("id4", null);
        String id5 = shar.getString("id5", null);
        queue = Volley.newRequestQueue(getActivity());
        url = VideoURL.KECHENG + "moKuaiId=" + id5 + "&banBenId=" + id4 + "&type=" + 4 + "&uid=" + 11945763 + "&p=" + 1;
        url1 = "http://www.91taoke.com/WeikeInterface/getList?moKuaiId=1000637&banBenId=77&type=4&uid=11945763&p=1";
        if (id5 != null && id4 != null) {
            VolleyUtils.connectionGET(url, ExerciseFragment.this, queue);
        } else {
            VolleyUtils.connectionGET(url1, ExerciseFragment.this, queue);
        }
    }


    @Override
    public void getRegister(String message) {
        Gson gson = new Gson();
        Obj json = gson.fromJson(message, Obj.class);
        DataBean data = json.getData();
        int code = json.getCode();
        if (code == 0) {
            list = data.getList();
            mAdapter = new MicroletureAdapter(getActivity());
            mAdapter.setView(list);
            mXlist.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
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

    // 下拉刷新
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

    //点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String id1 = list.get(position - 1).getId();
        String name = list.get(position - 1).getName();
        int payly = list.get(position - 1).getPayly();
        Intent intent = new Intent(getActivity(), ExerciseWebActivity.class);
        intent.putExtra("id1", id1);
        intent.putExtra("name", name);
        intent.putExtra("payly", payly);
        startActivity(intent);
    }

    //数据调用
    public static List<ListBean> getData() {
        return list;
    }


}
