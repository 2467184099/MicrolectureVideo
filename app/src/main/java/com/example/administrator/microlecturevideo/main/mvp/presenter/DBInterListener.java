package com.example.administrator.microlecturevideo.main.mvp.presenter;

import com.example.administrator.microlecturevideo.main.mvp.model.ThreadInfo;

import java.util.List;

/**
 * 数据访问接口
 */

public interface DBInterListener {
    /**
     * 插入线程信息
     *
     * @param threadInfo
     */
    public  void instertThread(ThreadInfo threadInfo);

    /**
     * 删除线程信息
     *
     * @param url
     * @param id
     */
    public void deleteThread(String url, int id);

    /**
     * 更新线程下载进度信息
     *
     * @param url
     * @param id
     * @param finished
     */
    public void upDataThread(String url, int id, int finished);

    /**
     * 查询文件的线程信息
     *
     * @param url
     * @return
     */
    public List<ThreadInfo> getThreads(String url);

    /**
     * 线程信息是在数据库中存在否存在
     * @param url
     * @param id
     * @return
     */
    public boolean isExists(String url, int id);

}
