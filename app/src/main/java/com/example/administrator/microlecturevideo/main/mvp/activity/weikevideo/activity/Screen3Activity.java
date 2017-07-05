package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.ScreenAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener1;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener2;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener3;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener4;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 我的课程筛选
 */
public class Screen3Activity extends AppCompatActivity implements View.OnClickListener, OnLoadRegisterListener, AdapterView.OnItemClickListener, OnLoadRegisterListener1, OnLoadRegisterListener2, OnLoadRegisterListener3, OnLoadRegisterListener4 {
    private ImageView mBack;
    private Button mBtn;
    //学段
    private GridView mGridView1;
    //年级
    private GridView mGridView2;
    //学科
    private GridView mGridView3;
    //版本
    private GridView mGridView4;
    //模块
    private GridView mGridView5;
    RequestQueue mQueue;
    Map<String, String> map;
    //学段
    ArrayList<HashMap<String, String>> mList = new ArrayList<>();
    //年级
    ArrayList<HashMap<String, String>> mList1 = new ArrayList<>();
    //科目
    ArrayList<HashMap<String, String>> mList2 = new ArrayList<>();
    //版本
    ArrayList<HashMap<String, String>> mList3 = new ArrayList<>();
    //模块
    ArrayList<HashMap<String, String>> mList4 = new ArrayList<>();
    ScreenAdapter adapter;
    Bundle bundle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);
        intent = this.getIntent();
        bundle = intent.getExtras();
        //初始化控件
        initView();
        mQueue = Volley.newRequestQueue(this);
        getVolley();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_screen3);
        mBtn = (Button) findViewById(R.id.btn_screen3);
        // 基础信息
        getJiChu();
        mBack.setOnClickListener(this);
        mBtn.setOnClickListener(this);
    }

    /**
     * 基础信息
     */
    private void getJiChu() {
        //学段
        mGridView1 = (GridView) findViewById(R.id.gridview_screen3_1);
        mGridView1.setOnItemClickListener(this);
        //年级
        mGridView2 = (GridView) findViewById(R.id.gridview_screen3_2);
        mGridView2.setOnItemClickListener(this);
        //学科
        mGridView3 = (GridView) findViewById(R.id.gridview_screen3_3);
        mGridView3.setOnItemClickListener(this);
        //版本
        mGridView4 = (GridView) findViewById(R.id.gridview_screen3_4);
        mGridView4.setOnItemClickListener(this);
        //模块
        mGridView5 = (GridView) findViewById(R.id.gridview_screen3_5);
        mGridView5.setOnItemClickListener(this);

    }

    //请求数据
    private void getVolley() {
        //学段
        VolleyUtils.connectionGET(VideoURL.JICHUFENLEI + "fid=" + 1 + "&level=" + 1, this, mQueue);
        SharedPreferences preferences = this.getSharedPreferences("baby", Context.MODE_PRIVATE);
        String id1 = preferences.getString("id1", null);
        //年级
        VolleyUtils.connectionGET1(VideoURL.JICHUFENLEI + "fid=" + id1 + "&level=" + 2, Screen3Activity.this, mQueue);
        SharedPreferences preferences1 = this.getSharedPreferences("baby1", Context.MODE_PRIVATE);
        String id2 = preferences1.getString("id2", null);
        //科目
        VolleyUtils.connectionGET2(VideoURL.JICHUFENLEI + "fid=" + id2 + "&level=" + 3, Screen3Activity.this, mQueue);
        SharedPreferences preferences2 = this.getSharedPreferences("baby2", Context.MODE_PRIVATE);
        String id3 = preferences2.getString("id3", null);
        //版本
        VolleyUtils.connectionGET3(VideoURL.JICHUFENLEI + "fid=" + id3 + "&level=" + 4, Screen3Activity.this, mQueue);
        SharedPreferences preferences3 = this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
        String id4 = preferences3.getString("id4", null);
        //模块
        VolleyUtils.connectionGET4(VideoURL.MOKUAI + "fid=" + id4, Screen3Activity.this, mQueue);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_screen3:
                bundle.putString("ccc", "back");//添加要返回给页面1的数据
                intent.putExtras(bundle);
                this.setResult(Activity.RESULT_OK, intent);//返回页面1
                this.finish();
                break;
            case R.id.btn_screen3:
                SharedPreferences preferences3 = this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
                String id4 = preferences3.getString("id4", null);
                SharedPreferences preferences4 = Screen3Activity.this.getSharedPreferences("baby4", Context.MODE_PRIVATE);
                String id5 = preferences4.getString("id5", null);
                Log.e("=====", "id4=" + id4 + "id5=" + id5);
                bundle.putString("ccc", "back");
                bundle.putString("id4", id4);
                bundle.putString("id5", id5);
                intent.putExtras(bundle);
                this.setResult(Activity.RESULT_OK, intent);//返回页面1
                this.finish();
                break;
        }
    }

    //点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            //学段
            case R.id.gridview_screen3_1:
                HashMap<String, String> hashMap = mList.get(position);
                String id1 = hashMap.get("id");
                //  Log.e("abc", id1 + "");
                SharedPreferences preferences = Screen3Activity.this.getSharedPreferences("baby", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("id1", id1);
                edit.commit();
                VolleyUtils.connectionGET1(VideoURL.JICHUFENLEI + "fid=" + id1 + "&level=" + 2, Screen3Activity.this, mQueue);
                break;
            //年级
            case R.id.gridview_screen3_2:
                HashMap<String, String> hashMap1 = mList1.get(position);
                String id2 = hashMap1.get("id");
                // Log.e("abc", id2 + "");
                SharedPreferences preferences1 = Screen3Activity.this.getSharedPreferences("baby1", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit1 = preferences1.edit();
                edit1.putString("id2", id2);
                edit1.commit();
                VolleyUtils.connectionGET2(VideoURL.JICHUFENLEI + "fid=" + id2 + "&level=" + 3, Screen3Activity.this, mQueue);
                break;
            //科目
            case R.id.gridview_screen3_3:
                HashMap<String, String> hashMap2 = mList2.get(position);
                String id3 = hashMap2.get("id");
                // Log.e("abc", "id3=" + id3 + "");
                SharedPreferences preferences2 = Screen3Activity.this.getSharedPreferences("baby2", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit2 = preferences2.edit();
                edit2.putString("id3", id3);
                edit2.commit();
                VolleyUtils.connectionGET3(VideoURL.JICHUFENLEI + "fid=" + id3 + "&level=" + 4, Screen3Activity.this, mQueue);
                break;
            //版本
            case R.id.gridview_screen3_4:
                HashMap<String, String> hashMap3 = mList3.get(position);
                String id4 = hashMap3.get("id");
                // Log.e("abc", id4 + "");
                SharedPreferences preferences3 = Screen3Activity.this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit3 = preferences3.edit();
                edit3.putString("id4", id4);
                edit3.commit();
                VolleyUtils.connectionGET4(VideoURL.MOKUAI + "fid=" + id4, Screen3Activity.this, mQueue);
                break;
            //模块
            case R.id.gridview_screen3_5:
                HashMap<String, String> hashMap4 = mList4.get(position);
                String id5 = hashMap4.get("id");
                //  Log.e("abc", "模块id5=" + id5 + "");
                SharedPreferences preferences4 = Screen3Activity.this.getSharedPreferences("baby4", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit4 = preferences4.edit();
                edit4.putString("id5", id5);
                edit4.commit();
                break;
        }
    }

    //学段
    @Override
    public void getRegister(String message) {
        //  Log.e("-----", message.toString());
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONArray data = jsonObject.getJSONArray("data");
            mList.clear();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject1 = data.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                map = new HashMap<>();
                map.put("id", id);
                map.put("name", name);
                mList.add((HashMap<String, String>) map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ScreenAdapter(this);
        adapter.getEvent(mList);
        mGridView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //年级
    @Override
    public void getRegister1(String message) {
        // Log.e("bbb", message + "");
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONArray data = jsonObject.getJSONArray("data");
            mList1.clear();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject1 = data.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                map = new HashMap<>();
                map.put("id", id);
                map.put("name", name);
                mList1.add((HashMap<String, String>) map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ScreenAdapter(this);
        adapter.getEvent(mList1);
        mGridView2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //科目
    @Override
    public void getRegister2(String message) {
        // Log.e("bbb", message + "");
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONArray data = jsonObject.getJSONArray("data");
            mList2.clear();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject1 = data.getJSONObject(i);
                String id = jsonObject1.getString("id");
                //Log.e("rrrr", "id=" + id);
                String name = jsonObject1.getString("name");
                map = new HashMap<>();
                map.put("id", id);
                map.put("name", name);
                mList2.add((HashMap<String, String>) map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ScreenAdapter(this);
        adapter.getEvent(mList2);
        mGridView3.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //版本
    @Override
    public void getRegister3(String message) {
        // Log.e("bbb", message + "");
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONArray data = jsonObject.getJSONArray("data");
            mList3.clear();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject1 = data.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                map = new HashMap<>();
                map.put("id", id);
                map.put("name", name);
                mList3.add((HashMap<String, String>) map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ScreenAdapter(this);
        adapter.getEvent(mList3);
        mGridView4.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //模块
    @Override
    public void getRegister4(String message) {
        // Log.e("123", message + "");
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONArray data = jsonObject.getJSONArray("data");
            mList4.clear();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject1 = data.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                map = new HashMap<>();
                map.put("id", id);
                map.put("name", name);
                mList4.add((HashMap<String, String>) map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ScreenAdapter(this);
        adapter.getEvent(mList4);
        mGridView5.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
