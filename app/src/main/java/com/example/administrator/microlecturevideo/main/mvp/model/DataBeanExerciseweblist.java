package com.example.administrator.microlecturevideo.main.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

public class DataBeanExerciseweblist {
    int totalPage;
    List<com.example.administrator.microlecturevideo.main.mvp.model.List> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<com.example.administrator.microlecturevideo.main.mvp.model.List> getList() {
        return list;
    }

    public void setList(List<com.example.administrator.microlecturevideo.main.mvp.model.List> list) {
        this.list = list;
    }
}
