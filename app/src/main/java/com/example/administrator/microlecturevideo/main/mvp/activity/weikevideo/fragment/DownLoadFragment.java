package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.MainActivity;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.VideoActivity1;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.MyDownLoadAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.MyDownLoadAdapter1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.maxwin.view.XListView;

/**
 * 我的下载
 */

public class DownLoadFragment extends Fragment implements AdapterView.OnItemClickListener, XListView.IXListViewListener, View.OnClickListener {
    public static TextView textView_redact, textView_all, textView_delete;
    LinearLayout linearLayout_downLoadFragment;
    ImageView mLeft_download;
    Handler mHandler;
    XListView mList;
    //文件的数组
    String[] files;

    String file;
    MyDownLoadAdapter1 myDownLoadAdapter1;
    MyDownLoadAdapter myDownLoadAdapter;
    private int checkNum; // 记录选中的条目数量

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.download, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        //初始化控件
        initView(view);
        getFile();


    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        mList = (XListView) view.findViewById(R.id.lst_download);
        textView_redact = (TextView) view.findViewById(R.id.download_redact);
        mLeft_download = (ImageView) view.findViewById(R.id.img_left_download);
        textView_all = (TextView) view.findViewById(R.id.downloadfragment_all);
        textView_delete = (TextView) view.findViewById(R.id.downloadfragment_delete);
        linearLayout_downLoadFragment = (LinearLayout) view.findViewById(R.id.downloadfragment_layout);
        textView_redact.setOnClickListener(this);
        textView_all.setOnClickListener(this);
        textView_delete.setOnClickListener(this);
        mList.setOnItemClickListener(this);
        //设置两个方法
        //上拉加载
        mList.setPullLoadEnable(true);
        //下拉刷新
        mList.setPullRefreshEnable(true);
        //上拉刷新和下拉加载都必须设置监听事件
        mList.setXListViewListener(this);

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
            }
        }, 1000);
    }

    //停止加载和刷新
    public void stop() {
        //停止加载和刷新
        mList.stopLoadMore();
        mList.stopRefresh();
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mList.setRefreshTime(dateFormat.format(new Date(System.currentTimeMillis())));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String[] files = filePath();
        file = files[position - 1];
        if (textView_redact.getText().equals("编辑")) {
            Intent intent = new Intent(getActivity(), VideoActivity1.class);
            intent.putExtra("file", file);
            startActivity(intent);
        } else if (textView_redact.getText().equals("完成")) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.mydownloadadapter1_checkbox);
            if (!checkBox.isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_redact:
                if (textView_redact.getText().equals("编辑")) {
                    MainActivity.linearLayout_main.setVisibility(View.GONE);
                    textView_redact.setText("完成");
                    linearLayout_downLoadFragment.setVisibility(View.VISIBLE);
                    myDownLoadAdapter1 = new MyDownLoadAdapter1(getActivity(), filePath());
                    mList.setAdapter(myDownLoadAdapter1);
                    myDownLoadAdapter1.notifyDataSetChanged();
                } else if (textView_redact.getText().equals("完成")) {
                    MainActivity.linearLayout_main.setVisibility(View.VISIBLE);
                    textView_redact.setText("编辑");
                    linearLayout_downLoadFragment.setVisibility(View.GONE);
                    myDownLoadAdapter = new MyDownLoadAdapter(getActivity(), filePath());
                    mList.setAdapter(myDownLoadAdapter);
                    myDownLoadAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.downloadfragment_all:
                if (textView_all.getText().equals("全选")) {
                    textView_all.setText("全不选");
                    for (int i = 0; i < files.length; i++) {
                        if (!myDownLoadAdapter1.getIsSelected().get(i)) {
                            myDownLoadAdapter1.getIsSelected().put(i, true);
                        }
                    }
                    // 数量设为list的长度
                    checkNum = files.length;
                    // 刷新listview和TextView的显示
                    dataChanged();
                } else if (textView_all.getText().equals("全不选")) {
                    textView_all.setText("全选");
                    for (int i = 0; i < files.length; i++) {
                        if (myDownLoadAdapter1.getIsSelected().get(i)) {
                            myDownLoadAdapter1.getIsSelected().put(i, false);
                            checkNum--;// 数量减1
                        }
                    }
                    // 刷新listview和TextView的显示
                    dataChanged();
                }
                break;
            case R.id.downloadfragment_delete:
                break;
        }
    }


    private void getFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            MyDownLoadAdapter myDownLoadAdapter = new MyDownLoadAdapter(getActivity(), filePath());
            mList.setAdapter(myDownLoadAdapter);
            myDownLoadAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "内存卡不可用", Toast.LENGTH_SHORT).show();
        }
    }

    public String[] filePath() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/91taoke/");
        if (file.exists()) {
            files = file.list();
            int length = files.length;

        } else {
            Toast.makeText(getContext(), file + "不存在", Toast.LENGTH_SHORT).show();
        }
        return files;
    }

    // 刷新listview
    private void dataChanged() {
        // 通知listView刷新
        myDownLoadAdapter1.notifyDataSetChanged();
    }

}
