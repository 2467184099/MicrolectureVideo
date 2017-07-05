package com.example.administrator.microlecturevideo.main.mvp.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.microlecturevideo.main.mvp.model.ThreadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问接口实现
 */

public class ThreadDAOImpl implements DBInterListener {
    private DBHeLper dbHeLper = null;

    public ThreadDAOImpl(Context context) {
        dbHeLper = new DBHeLper(context);
    }

    /**
     * 数据插入
     *
     * @param threadInfo
     */
    @Override
    public void instertThread(ThreadInfo threadInfo) {
        SQLiteDatabase database = dbHeLper.getWritableDatabase();
        database.execSQL("insert into thread_info(id,url,start,end,finished)values(?,?,?,?,?)",
                new Object[]{
                        threadInfo.getId(), threadInfo.getUrl(),
                        threadInfo.getStart(), threadInfo.getEnd(), threadInfo.getFinished()
                });
        database.close();
    }

    /**
     * 数据删除
     *
     * @param url
     * @param id
     */
    @Override
    public void deleteThread(String url, int id) {
        SQLiteDatabase database = dbHeLper.getWritableDatabase();
        database.execSQL("delete from thread_info where url = ? and id = ?",
                new Object[]{url, id});
        database.close();
    }

    /**
     * 数据更新
     *
     * @param url
     * @param id
     * @param finished
     */
    @Override
    public void upDataThread(String url, int id, int finished) {
        SQLiteDatabase database = dbHeLper.getWritableDatabase();
         database.execSQL("update thread_info set finished = ? where url = ? and id = ?",
                new Object[]{finished, url, id});
        database.close();
    }

    /**
     * 查询数据
     *
     * @param url
     * @return
     */
    @Override
    public List<ThreadInfo> getThreads(String url) {
        SQLiteDatabase database = dbHeLper.getWritableDatabase();
        List<ThreadInfo> list = new ArrayList<>();
        Cursor cursor =
                database.rawQuery("select * from thread_info where url = ?", new String[]{url});
        while (cursor.moveToNext()) {
            ThreadInfo threadInfo = new ThreadInfo();
            threadInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
            threadInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            threadInfo.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            threadInfo.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            threadInfo.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
            list.add(threadInfo);
        }
        cursor.close();
        database.close();
        return list;
    }

    /**
     * 查询线程信息是否存在
     * @param url
     * @param id
     * @return
     */
    @Override
    public boolean isExists(String url, int id) {
        SQLiteDatabase database = dbHeLper.getWritableDatabase();
        Cursor cursor =
                database.rawQuery("select * from thread_info where url = ? and id = ?",
                        new String[]{url, id + ""});
        boolean exisit = cursor.moveToNext();
        cursor.close();
        database.close();
        return exisit;
    }
}
