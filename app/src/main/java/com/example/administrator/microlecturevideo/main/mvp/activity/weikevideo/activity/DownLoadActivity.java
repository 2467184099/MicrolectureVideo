package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.model.FileInfo;
import com.example.administrator.microlecturevideo.main.mvp.presenter.DownLoadService;

public class DownLoadActivity extends AppCompatActivity {
    Intent intent;
    String url1;
    String name;
    TextView textView_name;
    ProgressBar progressBar;
    Button button_stop, button_start;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downlist);
        intent = this.getIntent();
        url1 = intent.getStringExtra("url");
        name = intent.getStringExtra("name");
        initView();
    }

    private void initView() {
        textView_name = (TextView) findViewById(R.id.downloaditem_name);
        progressBar = (ProgressBar) findViewById(R.id.downloaditem_progressBar);
        button_stop = (Button) findViewById(R.id.downloaditem_stop);
        button_start = (Button) findViewById(R.id.downloaditem_start);
        textView_name.setText(name);
        textView_name.setTextColor(Color.BLACK);
        progressBar.setMax(100);
        imageView = (ImageView) findViewById(R.id.download_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //创建文件信息对象
        final FileInfo fileInfo = new FileInfo(0, url1, name, 0, 0);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownLoadActivity.this, DownLoadService.class);
                intent.setAction(DownLoadService.ACTION_START);
                intent.putExtra("fileInfo", fileInfo);
                startService(intent);
            }
        });
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownLoadActivity.this, DownLoadService.class);
                intent.setAction(DownLoadService.ACTION_STOP);
                intent.putExtra("fileInfo", fileInfo);
                startService(intent);
            }
        });
        //注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownLoadService.ACTION_UPDATA);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * 更新ui的广播接收器
     */
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownLoadService.ACTION_UPDATA.equals(intent.getAction())) {
                //更新进度条
                int finished = intent.getIntExtra("finished", 0);
                progressBar.setProgress(finished);
            }

        }
    };
}