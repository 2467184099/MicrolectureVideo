package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.microlecturevideo.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MyDownLoadAdapter1 extends BaseAdapter {
    Context context;
    String[] files;
    Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    public MyDownLoadAdapter1(Context context, String[] files) {
        this.context = context;
        this.files = files;
        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < files.length; i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return null == files ? 0 : files.length;
    }

    @Override
    public Object getItem(int position) {
        return files[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if (convertView == null) {
            viewHoder = new ViewHoder();
            convertView = LayoutInflater.from(context).inflate(R.layout.mydownloadadapter1, parent, false);
            viewHoder.textView = (TextView) convertView.findViewById(R.id.mydownloadadapter1_textview);
            viewHoder.checkBox = (CheckBox) convertView.findViewById(R.id.mydownloadadapter1_checkbox);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        viewHoder.textView.setText(files[position]);


        // 根据isSelected来设置checkbox的选中状况
        viewHoder.checkBox.setChecked(getIsSelected().get(position));

        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }


    static class ViewHoder {
        TextView textView;
        CheckBox checkBox;
    }
}
