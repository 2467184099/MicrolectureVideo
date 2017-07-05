package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.administrator.microlecturevideo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 筛选适配器
 */

public class ScreenAdapter extends BaseAdapter {
    public Context mContext;
    ArrayList<HashMap<String, String>> mList;

    public ScreenAdapter(Context context) {
        this.mContext = context;
    }

    public void getEvent(ArrayList<HashMap<String, String>> mList) {
        this.mList = mList;
    }


    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if (convertView == null) {
            viewHoder = new ViewHoder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.screenadapter, parent, false);
            viewHoder.mTextView = (Button) convertView.findViewById(R.id.txt_screenadapter);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        viewHoder.mTextView.setText(mList.get(position).get("name"));
        viewHoder.mTextView.setTextSize(10);
        return convertView;
    }


    static class ViewHoder {
        Button mTextView;
    }


}
