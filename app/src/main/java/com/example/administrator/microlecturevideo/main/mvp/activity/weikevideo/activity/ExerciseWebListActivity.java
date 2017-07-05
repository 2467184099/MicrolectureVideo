package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.ExerciseWebListAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.model.DataBean1;
import com.example.administrator.microlecturevideo.main.mvp.model.DataBeanExerciseweblist;
import com.example.administrator.microlecturevideo.main.mvp.model.ExerciseWebListInfo;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * 习题微课习题列表
 */
public class ExerciseWebListActivity extends AppCompatActivity implements OnLoadRegisterListener, XListView.IXListViewListener {
    int groupPosition;
    int childPosition;
    String id;
    RequestQueue queue;
    String url;
    Handler mHandler;
    //返回
    private ImageView img_back;
    public static XListView listView;
    //数据源
    List<com.example.administrator.microlecturevideo.main.mvp.model.List> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_web_list);
        mHandler = new Handler();
        getString();
        initView();
        queue = Volley.newRequestQueue(this);
        url = VideoURL.XITILIEBIAO + "classId=" + id + "&p=" + 1;
        VolleyUtils.connectionGET(url, this, queue);

    }

    //初始化UI布局
    private void initView() {
        img_back = (ImageView) findViewById(R.id.execrise_web_list_img);
        listView = (XListView) findViewById(R.id.execrise_web_list_list);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //接收上一界面传递过来的数据
    private void getString() {
        Intent intent = this.getIntent();
        groupPosition = intent.getIntExtra("groupPosition", 0);
        childPosition = intent.getIntExtra("childPosition", 0);
        List<DataBean1> data = ExerciseWebActivity.data;
        id = data.get(groupPosition).getChildList().get(childPosition).getId();
       // Log.e("-----", "id=" + id);
    }

    //数据处理
    @Override
    public void getRegister(String message) {
      //  Log.e("=====", message.toString());
        Gson gson = new Gson();
        ExerciseWebListInfo exerciseWebListInfo = gson.fromJson(message, ExerciseWebListInfo.class);
        int code = exerciseWebListInfo.getCode();
        if (code == 0) {
            DataBeanExerciseweblist data = exerciseWebListInfo.getData();
            list = data.getList();
            ExerciseWebListAdapter exerciseWebListAdapter = new ExerciseWebListAdapter(this, list);
            listView.setAdapter(exerciseWebListAdapter);
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
        listView.setPullLoadEnable(true);
        //下拉刷新
        listView.setPullRefreshEnable(true);
        //上拉刷新和下拉加载都必须设置监听事件
        listView.setXListViewListener(this);
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
                Toast.makeText(ExerciseWebListActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }

    //停止加载和刷新
    public void stop() {
        //停止加载和刷新
        listView.stopLoadMore();
        listView.stopRefresh();
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        listView.setRefreshTime(dateFormat.format(new Date(System.currentTimeMillis())));
    }


}
