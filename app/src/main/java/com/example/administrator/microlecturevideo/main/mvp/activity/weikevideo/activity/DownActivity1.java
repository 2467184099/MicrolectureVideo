package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
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
 * 下载
 */
public class DownActivity1 extends AppCompatActivity implements View.OnClickListener, OnLoadRegisterListener, XListView.IXListViewListener {
    //回退建
    ImageView imageView_back;
    XListView xListView;
    String url;
    Handler mHandler;
    String urlArr1;
    //数据源
    List<FreeInfo.DataBean.ListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        initView();
        mHandler = new Handler();
        SharedPreferences shar = this.getSharedPreferences("test1", Context.MODE_PRIVATE);
        String id = shar.getString("id", null);
        //Log.e("------", "id=" + id);
        url = VideoURL.MIANFEI + "subjectId=" + id + "&p=" + 1;
        RequestQueue queue = Volley.newRequestQueue(this);
        VolleyUtils.connectionGET(url, this, queue);
    }

    //初始化UI布局
    private void initView() {
        imageView_back = (ImageView) findViewById(R.id.down_back);
        xListView = (XListView) findViewById(R.id.down_xlistview);
        imageView_back.setOnClickListener(this);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<String> urlArr = list.get(position - 1).getUrlArr();
                String name = list.get(position-1).getName();
                for (int i = 0; i < urlArr.size(); i++) {
                    urlArr1 = urlArr.get(i);
                }
                String url1 = urlArr1.replace(".flv", ".mp4");
                Intent intent = new Intent(DownActivity1.this, DownLoadActivity.class);
                intent.putExtra("url", url1);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.down_back:
                finish();
                break;
        }
    }


    //数据处理
    @Override
    public void getRegister(String message) {
        Gson gson = new Gson();
        FreeInfo freeInfo = gson.fromJson(message, FreeInfo.class);
        FreeInfo.DataBean data = freeInfo.getData();
        int code = freeInfo.getCode();
        if (code == 0) {
            list = data.getList();
            FreeAdapter freeAdapter = new FreeAdapter(this);
            freeAdapter.getTest(list);
            xListView.setAdapter(freeAdapter);
            freeAdapter.notifyDataSetChanged();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
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
        xListView.setPullLoadEnable(true);
        //下拉刷新
        xListView.setPullRefreshEnable(true);
        //上拉刷新和下拉加载都必须设置监听事件
        xListView.setXListViewListener(this);

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
                Toast.makeText(DownActivity1.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    //停止加载和刷新
    public void stop() {
        //停止加载和刷新
        xListView.stopLoadMore();
        xListView.stopRefresh();
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        xListView.setRefreshTime(dateFormat.format(new Date(System.currentTimeMillis())));
    }
}

