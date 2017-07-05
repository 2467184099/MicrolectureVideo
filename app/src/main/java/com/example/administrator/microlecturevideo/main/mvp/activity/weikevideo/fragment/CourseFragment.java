package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.ExerciseWebActivity;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.Screen3Activity;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.VideoWebActivity1;
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
 * 我的课程列表
 */

public class CourseFragment extends Fragment implements View.OnClickListener, OnLoadRegisterListener, XListView.IXListViewListener, AdapterView.OnItemClickListener {
    XListView mXlist;
    ImageView mLeft, mRight;
    Handler mHandler;
    MicroletureAdapter mAdapter;
    static List<ListBean> list;
    String url;
    RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        //初始化控件
        initView(view);
        url = VideoURL.WODEKECHENG + "moKuaiId=" + id5 + "&banBenId=" + id4 + "&uid=" + 11945763 + "&p=" + 1;
        queue = Volley.newRequestQueue(getActivity());
        VolleyUtils.connectionGET(url, CourseFragment.this, queue);

    }


    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        mXlist = (XListView) view.findViewById(R.id.lst_course);
        mXlist.setOnItemClickListener(this);
        mLeft = (ImageView) view.findViewById(R.id.img_left_course);
        mRight = (ImageView) view.findViewById(R.id.img_right_course);
        mRight.setOnClickListener(this);
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

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
                url = VideoURL.WODEKECHENG + "moKuaiId=" + id5 + "&banBenId=" + id4 + "&uid=" + 11945763 + "&p=" + 1;
                VolleyUtils.connectionGET(url, CourseFragment.this, queue);
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stop();
                url = VideoURL.WODEKECHENG + "moKuaiId=" + id5 + "&banBenId=" + id4 + "&uid=" + 11945763 + "&p=" + 2;
                VolleyUtils.connectionGET(url, CourseFragment.this, queue);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_right_course:
                Intent intent = new Intent();
                intent.setClass(getActivity(), Screen3Activity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            case R.id.img_left_course:
                break;
        }
    }

    /**
     * xListView条目的点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String id1 = list.get(position - 1).getId();
        String name = list.get(position - 1).getName();
        String type = list.get(position - 1).getType();
        if (name == null) {
            Intent intent = new Intent(getActivity(), VideoWebActivity1.class);
            intent.putExtra("position", position);
            startActivity(intent);
        } else if (name != null) {
            if (type.equals("4")) {
                Intent intent = new Intent(getActivity(), ExerciseWebActivity.class);
                intent.putExtra("id1", id1);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), VideoWebActivity1.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        }
    }

    //数据调用
    public static List<ListBean> getData() {
        return list;
    }

    String id5;
    String id4;

    /**
     * 接收回传数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String ccc = bundle.getString("ccc");
            id5 = bundle.getString("id5");
            id4 = bundle.getString("id4");
            Log.e("123", "id5=" + id5 + "id4=" + id4);

        }
    }
}