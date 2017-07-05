package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.model.ListBean;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * 课程列表适配器
 */

public class MicroletureAdapter extends BaseAdapter implements OnLoadRegisterListener {

    public List<ListBean> mList;
    public Context mContext;

    public MicroletureAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setView(List<ListBean> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.microltureadapter, parent, false);
            viewHodler.textView_name = (TextView) convertView.findViewById(R.id.txt_name);
            viewHodler.textView_count = (TextView) convertView.findViewById(R.id.txt_count);
            viewHodler.textView_money = (TextView) convertView.findViewById(R.id.txt_money);
            viewHodler.mBtn = (Button) convertView.findViewById(R.id.btn_mic);
            viewHodler.mImg = (ImageView) convertView.findViewById(R.id.img_icon);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        Picasso.with(mContext).load(mList.get(position).getPic()).into(viewHodler.mImg);
        viewHodler.textView_name.setText(mList.get(position).getName());
        viewHodler.textView_name.setTextColor(Color.BLACK);
        viewHodler.textView_count.setText("共" + mList.get(position).getClanum() + "讲");
        viewHodler.textView_count.setTextColor(Color.BLACK);
        viewHodler.textView_money.setText(mList.get(position).getPrice());
        viewHodler.textView_money.setTextColor(Color.BLACK);
        int payly = mList.get(position).getPayly();
        if (payly == 1 || payly == 0) {
            viewHodler.mBtn.setText("已购买");
            viewHodler.mBtn.setTextColor(Color.BLACK);
            viewHodler.mBtn.setBackgroundResource(R.color.gray2);
        }
        if (payly == 2) {
            viewHodler.mBtn.setText("购买");
            viewHodler.mBtn.setBackgroundResource(R.color.green);
        }
        final ViewHodler finalViewHodler = viewHodler;
        final String id = mList.get(position).getId();
        Log.e("852258", "id=" + id);
        viewHodler.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHodler.mBtn.getText().equals("购买")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                    dialog.setMessage("是否购买《" + mList.get(position).getName() + "》");
                    dialog.setTitle("提示");
                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            RequestQueue queue = Volley.newRequestQueue(mContext);
                            String url = VideoURL.GOUMAI + "&uid=" + 11945763 + "&tid=" + id;
                            VolleyUtils.connectionGET(url, MicroletureAdapter.this, queue);


                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.create().show();
                }
            }
        });

        return convertView;
    }

    @Override
    public void getRegister(String message) {
        Log.e("======", message.toString());
        try {
            JSONObject jsonObject = new JSONObject(message);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                Toast.makeText(mContext, "购买成功", Toast.LENGTH_SHORT).show();
                ViewHodler.mBtn.setText("已购买");
                ViewHodler.mBtn.setTextColor(Color.BLACK);
                ViewHodler.mBtn.setBackgroundResource(R.color.gray2);
                notifyDataSetChanged();
            } else if (code == 1) {
                Toast.makeText(mContext, "该配套课时已购买过，无需再次购买", Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    static class ViewHodler {
        ImageView mImg;
        TextView textView_name;
        TextView textView_count;
        TextView textView_money;
        static Button mBtn;
    }
}
