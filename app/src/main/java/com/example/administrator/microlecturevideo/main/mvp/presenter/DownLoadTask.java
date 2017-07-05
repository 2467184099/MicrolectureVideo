package com.example.administrator.microlecturevideo.main.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.administrator.microlecturevideo.main.mvp.model.FileInfo;
import com.example.administrator.microlecturevideo.main.mvp.model.ThreadInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 下载任务类
 */

public class DownLoadTask {
    //上下文
    private Context context = null;
    //文件信息
    private FileInfo fileInfo = null;
    private DBInterListener threadDAO = null;
    private int finished = 0;
    public boolean isPasue = false;



    public DownLoadTask(Context context, FileInfo fileInfo) {
        this.context = context;
        this.fileInfo = fileInfo;
        threadDAO = new ThreadDAOImpl(context);
    }

    /**
     * 下载
     */
    public void downLoad() {
        //读取数据库的线程信息
        List<ThreadInfo> threads = threadDAO.getThreads(fileInfo.getUrl());
        ThreadInfo threadInfo=null;
        if (threads.size()==0){
            threadInfo=new ThreadInfo(0,fileInfo.getUrl(),0,fileInfo.getLength(),0);
        }else {
            threadInfo=threads.get(0);
        }
        //创建子线程进行下载
        new DownLoadThread(threadInfo).start();
    }


    /**
     * 数据下载线程
     */
    class DownLoadThread extends Thread {
        //线程信息
        private ThreadInfo threadInfo = null;
        public DownLoadThread(ThreadInfo threadInfo) {
            this.threadInfo = threadInfo;
        }

        public void run() {
            //向数据库插入线程信息
            if (!threadDAO.isExists(threadInfo.getUrl(), threadInfo.getId())) {
                threadDAO.instertThread(threadInfo);
            }

            HttpURLConnection urlConnection = null;
            RandomAccessFile accessFile = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(threadInfo.getUrl());
                urlConnection = (HttpURLConnection) url.openConnection();
                //网络请求方法
                urlConnection.setRequestMethod("GET");
                //设置链接超时
                urlConnection.setConnectTimeout(3000);
                // 设置下载位置
                int start = threadInfo.getStart() + threadInfo.getFinished();
                //设置下载范围
                urlConnection.setRequestProperty("Range", "bytes=" + start + "-" + threadInfo.getEnd());
                //  设置文件写入位置
                File file = new File(DownLoadService.DOWNLOAD_PATA, fileInfo.getName());
                accessFile = new RandomAccessFile(file, "rwd");
                accessFile.seek(start);

                Intent intent = new Intent(DownLoadService.ACTION_UPDATA);
                finished += threadInfo.getFinished();
                // 开始下载
                if (urlConnection.getResponseCode() == 206) {
                    //读取数据
                    inputStream = urlConnection.getInputStream();
                    byte[] bytes = new byte[1024 * 4];
                    int len = -1;
                    long time = System.currentTimeMillis();
                    while ((len = inputStream.read(bytes)) != -1) {
                        // 写入文件
                        accessFile.write(bytes, 0, len);
                        //累加整个文件的完成进度
                        finished += len;
                        if (System.currentTimeMillis() - time > 500) {
                            //间隔500毫秒更新一次进度
                            time = System.currentTimeMillis();
                            // 把下载进度通过广播发送给activity
                            intent.putExtra("finished", finished * 100 / fileInfo.getLength());
                            context.sendBroadcast(intent);
                        }
                        if (isPasue) {
                            //保存进度到数据库
                            threadDAO.upDataThread(threadInfo.getUrl(), threadInfo.getId(), finished);
                            return;
                        }
                    }

                }
                //删除线程信息
                threadDAO.deleteThread(threadInfo.getUrl(), threadInfo.getId());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    urlConnection.disconnect();
                    accessFile.close();
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
