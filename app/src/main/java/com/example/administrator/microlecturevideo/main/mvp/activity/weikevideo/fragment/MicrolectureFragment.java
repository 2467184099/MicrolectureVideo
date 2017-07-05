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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.VideoWebActivity;
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
 * 微课视频列表
 */

public class MicrolectureFragment extends Fragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener, OnLoadRegisterListener {
    XListView mXlist;
    Handler mHandler;
    MicroletureAdapter mAdapter;
    static List<ListBean> list;
    String url;
    String url1;
    RequestQueue queue;

    /**
     * code : 0
     * info : 查询成功
     * data : {"list":[{"id":"2769","name":"微素材·点亮作文","price":"20.00","clanum":"40","pic":"http://www.91taoke.com/images/photo/1440730460.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"2759","name":"高一语文聆听美文2 人教版","price":"10.00","clanum":"65","pic":"http://www.91taoke.com/images/photo/1440729843.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"1185","name":"高一语文上（必修1、2）  人教版古诗文 ","price":"80.00","clanum":"31","pic":"http://www.91taoke.com/images/photo/1404436094.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"1183","name":"高一语文下（必修3、4）  人教版古诗文","price":"80.00","clanum":"30","pic":"http://www.91taoke.com/images/photo/1404436107.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"171","name":"高一语文聆听美文 人教版","price":"10.00","clanum":"76","pic":"http://www.91taoke.com/images/photo/1483406859.jpg","type":"1","kecheng_id":"1001021","payly":1},{"id":"163","name":"高一语文特色专题课程 人教版","price":"10.00","clanum":"15","pic":"http://www.91taoke.com/images/photo/1403313839.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"143","name":"高一语文上学期期末备考课程 人教版","price":"30.00","clanum":"12","pic":"http://www.91taoke.com/images/photo/1403310687.png","type":"2","kecheng_id":"1001021","payly":1},{"id":"135","name":"高一语文上学期期中备考课程 人教版","price":"40.00","clanum":"13","pic":"http://www.91taoke.com/images/photo/1403310150.png","type":"2","kecheng_id":"1001021","payly":1},{"id":"65","name":"高一语文重难点突破课程 人教版","price":"60.00","clanum":"28","pic":"http://www.91taoke.com/images/photo/1403253776.png","type":"1","kecheng_id":"1001021","payly":1}],"totalPage":1}
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.microlectre, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("coustom", Context.MODE_PRIVATE);
        //模块
        String id5 = preferences.getString("id5", null);
        //版本
        String id4 = preferences.getString("id4", null);
        //资源
        String type = preferences.getString("type", null);
        Log.e("8888888","id5="+id5+"id4="+id4+"type="+type);
        queue = Volley.newRequestQueue(getActivity());
        url = VideoURL.KECHENG + "moKuaiId=" + id5 + "&banBenId=" + id4 + "&type=" + type + "&uid=" + 11945763 + "&p=" + 1;
        url1 = "http://www.91taoke.com/WeikeInterface/getList?moKuaiId=1001021&banBenId=95&type=0&uid=11945763&p=1";
        if (id5 != null && id4 != null && type != null) {
            VolleyUtils.connectionGET(url, MicrolectureFragment.this, queue);
        } else {

            VolleyUtils.connectionGET(url1, MicrolectureFragment.this, queue);
        }
        mXlist = (XListView) view.findViewById(R.id.list_microlectre);
        mXlist.setOnItemClickListener(this);
        mHandler = new Handler();

    }

    // 数据处理
    @Override
    public void getRegister(String message) {
        Log.e("----",message.toString());
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

    //点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), VideoWebActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    //数据调用
    public static List<ListBean> getData() {
        return list;
    }


}




