package com.example.administrator.microlecturevideo.main.mvp.model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/17.
 */

public class ChargeInfo {
    int code;
    String info;
    ArrayList<String> data;

    public ChargeInfo() {
    }

    public ChargeInfo(int code, String info, ArrayList<String> data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChargeInfo{" +
                "code=" + code +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
