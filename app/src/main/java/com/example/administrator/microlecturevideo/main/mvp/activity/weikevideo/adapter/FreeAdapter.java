package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.model.FreeInfo;

import java.util.List;

/**
 * 视频详情课程列表
 */

public class FreeAdapter extends BaseAdapter {
    Context mContext;
    List<FreeInfo.DataBean.ListBean> list;
     public FreeAdapter(Context mContext){
         this.mContext=mContext;
     }
     public void getTest( List<FreeInfo.DataBean.ListBean> list){
         this.list=list;

     }
    @Override
    public int getCount() {
        return null==list?0:list.size();
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
          ViewHodler viewHodler=null;
        if (convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.freeadapter,parent,false);
            viewHodler.mText= (TextView) convertView.findViewById(R.id.txt_freeadapter);
          convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.mText.setText(list.get(position).getName());
        return convertView;
    }
    static class ViewHodler {
        TextView mText;
    }
}
