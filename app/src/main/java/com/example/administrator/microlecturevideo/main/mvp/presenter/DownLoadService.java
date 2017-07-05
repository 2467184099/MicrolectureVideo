package com.example.administrator.microlecturevideo.main.mvp.presenter;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.microlecturevideo.main.mvp.model.FileInfo;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/12.
 */

public class DownLoadService extends Service {
    //开始下载命令
    public static final String ACTION_START = "ACTION_START";
    //停止下载命令
    public static final String ACTION_STOP = "ACTION_STOP";
    //更新下载命令
    public static final String ACTION_UPDATA = "ACTION_UPDATA";
    //下载路径
    public static final String DOWNLOAD_PATA =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/91taoke/";
    //初始化标示
    public static final int MSG_INIT = 0;
    private DownLoadTask downLoadTask = null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获得activity传过来的参数
        if (ACTION_START.equals(intent.getAction())) {
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
            Log.e("---------", fileInfo.toString());
            //启动初始化线程
            new InitThread(fileInfo).start();
        } else if (ACTION_STOP.equals(intent.getAction())) {
            if (downLoadTask!=null){
                downLoadTask.isPasue=true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    Log.e("1233", "fileInfo=" + fileInfo);
                    //启动下载任务
                    downLoadTask = new DownLoadTask(DownLoadService.this, fileInfo);
                    downLoadTask.downLoad();
                    break;
            }
        }
    };

    /**
     * 初始化子线程
     */
    class InitThread extends Thread {
        private FileInfo fileInfo;

        public InitThread(FileInfo fileInfo) {
            this.fileInfo = fileInfo;
        }

        public void run() {
            HttpURLConnection urlConnection = null;
            RandomAccessFile randomAccessFile = null;
            try {
                //连接网络文件
                URL url = new URL(fileInfo.getUrl());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(3000);
                urlConnection.setRequestMethod("GET");
                int length = -1;
                if (urlConnection.getResponseCode() == 200) {
                    // 获得文件长度
                    length = urlConnection.getContentLength();
                }
                if (length <= 0) {
                    return;
                }
                File dir = new File(DOWNLOAD_PATA);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                // 在本地创建文件
                File file = new File(dir, fileInfo.getName());
                //随机访问
                randomAccessFile = new RandomAccessFile(file, "rwd");
                // 设置文件长度
                randomAccessFile.setLength(length);
                fileInfo.setLength(length);
                handler.obtainMessage(MSG_INIT, fileInfo).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    urlConnection.disconnect();
                    randomAccessFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
