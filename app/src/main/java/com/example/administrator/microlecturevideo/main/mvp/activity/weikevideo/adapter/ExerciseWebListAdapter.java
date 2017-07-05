package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.VideoActivity;
import com.example.administrator.microlecturevideo.main.mvp.model.List;

/**
 * 习题微课习题列表适配器
 */

public class ExerciseWebListAdapter extends BaseAdapter {
    Context context;
    java.util.List<List> list;

    public ExerciseWebListAdapter(Context context, java.util.List<List> list) {
        this.context = context;
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.exerciseweblistadapter, parent, false);
            viewHodler.text_topic = (TextView) convertView.findViewById(R.id.text_topic);
            viewHodler.text_video = (TextView) convertView.findViewById(R.id.text_video);
            viewHodler.btn = (Button) convertView.findViewById(R.id.text_video);
            viewHodler.webView_topic = (TextView) convertView.findViewById(R.id.webview_topic);
            viewHodler.webView_answer = (TextView) convertView.findViewById(R.id.webview_answer);
            viewHodler.webView_resolve = (TextView) convertView.findViewById(R.id.webview_resolve);
            viewHodler.linearLayout_answer = (LinearLayout) convertView.findViewById(R.id.linearlayput_answer);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        viewHodler.text_topic.setText("第" + (position + 1) + "题");
        viewHodler.text_video.setText("视频解析");
        viewHodler.text_video.setBackgroundResource(R.color.green);
        viewHodler.webView_topic.setText(Html.fromHtml(list.get(position).getTopic()));
        viewHodler.webView_answer.setText(Html.fromHtml(list.get(position).getAnswer()));
        viewHodler.webView_resolve.setText(Html.fromHtml(list.get(position).getResolve()));
        viewHodler.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String viewUrl = list.get(position).getViewUrl();
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("viewUrl", viewUrl);
                context.startActivity(intent);
            }
        });
        final ViewHodler finalViewHodler = viewHodler;
        finalViewHodler.linearLayout_answer.setVisibility(View.GONE);

        viewHodler.webView_topic.setOnClickListener(new View.OnClickListener() {
            private int touchCount = 0; //点击的次数

            @Override
            public void onClick(View v) {
                touchCount += 1;
                if (touchCount == 1) {
                    finalViewHodler.linearLayout_answer.setVisibility(View.VISIBLE);
                }
                if (touchCount == 2) {
                    finalViewHodler.linearLayout_answer.setVisibility(View.GONE);
                    touchCount = 0;
                }
            }

        });

        return convertView;
    }


    static class ViewHodler {
        TextView text_topic, text_video;
        TextView webView_topic, webView_answer, webView_resolve;
        LinearLayout linearLayout_answer;
        Button btn;
    }
}
