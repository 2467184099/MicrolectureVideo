package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.microlecturevideo.R;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MyDownLoadAdapter extends BaseAdapter {
    Context context;
    String[] files;

    public MyDownLoadAdapter(Context context, String[] files) {
        this.context = context;
        this.files = files;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mydownloadadapter, parent, false);
            viewHoder.imageView = (ImageView) convertView.findViewById(R.id.mydownloadadapter_imageview);
            viewHoder.textView = (TextView) convertView.findViewById(R.id.mydownloadadapter_textview);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        viewHoder.textView.setText(files[position]);
        return convertView;
    }
    static class ViewHoder {
        ImageView imageView;
        TextView textView;
    }
}
