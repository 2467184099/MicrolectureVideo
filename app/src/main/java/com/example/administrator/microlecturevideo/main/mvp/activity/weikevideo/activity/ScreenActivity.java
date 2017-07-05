package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.ScreenAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener1;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener2;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener3;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener4;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener5;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 微课视频筛选
 */
public class ScreenActivity extends AppCompatActivity implements View.OnClickListener, OnLoadRegisterListener, AdapterView.OnItemClickListener, OnLoadRegisterListener1, OnLoadRegisterListener2, OnLoadRegisterListener3, OnLoadRegisterListener4, OnLoadRegisterListener5 {
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
    //分类
    private GridView mGridView6;
    //分类区域
    private LinearLayout mLinearLayout;
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
    //资源分类
    ArrayList<HashMap<String, String>> mList5 = new ArrayList<>();
    ScreenAdapter adapter;
    Bundle bundle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
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
        mBack = (ImageView) findViewById(R.id.img_screen);
        mBtn = (Button) findViewById(R.id.btn_screen);
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
        mGridView1 = (GridView) findViewById(R.id.gridview_1);
        mGridView1.setOnItemClickListener(this);
        //年级
        mGridView2 = (GridView) findViewById(R.id.gridview_2);
        mGridView2.setOnItemClickListener(this);
        //学科
        mGridView3 = (GridView) findViewById(R.id.gridview_3);
        mGridView3.setOnItemClickListener(this);
        //版本
        mGridView4 = (GridView) findViewById(R.id.gridview_4);
        mGridView4.setOnItemClickListener(this);
        //模块
        mGridView5 = (GridView) findViewById(R.id.gridview_5);
        mGridView5.setOnItemClickListener(this);
        //分类
        mGridView6 = (GridView) findViewById(R.id.gridview_6);
        mGridView6.setOnItemClickListener(this);
        //分类区域
        mLinearLayout = (LinearLayout) findViewById(R.id.layout_screen);

    }

    //请求数据
    private void getVolley() {
        //学段
        VolleyUtils.connectionGET(VideoURL.JICHUFENLEI + "fid=" + 1 + "&level=" + 1, this, mQueue);
        //资源分类
        VolleyUtils.connectionGET5(VideoURL.ZIYUAN, ScreenActivity.this, mQueue);
        SharedPreferences preferences = this.getSharedPreferences("baby", Context.MODE_PRIVATE);
        String id1 = preferences.getString("id1", null);
        //Log.e("=====", "id1=" + id1);
        //年级
        VolleyUtils.connectionGET1(VideoURL.JICHUFENLEI + "fid=" + id1 + "&level=" + 2, ScreenActivity.this, mQueue);
        SharedPreferences preferences1 = this.getSharedPreferences("baby1", Context.MODE_PRIVATE);
        String id2 = preferences1.getString("id2", null);
        // Log.e("=====", "id2=" + id2);
        //科目
        VolleyUtils.connectionGET2(VideoURL.JICHUFENLEI + "fid=" + id2 + "&level=" + 3, ScreenActivity.this, mQueue);
        SharedPreferences preferences2 = this.getSharedPreferences("baby2", Context.MODE_PRIVATE);
        String id3 = preferences2.getString("id3", null);
        //Log.e("=====", "id3=" + id3);
        //版本
        VolleyUtils.connectionGET3(VideoURL.JICHUFENLEI + "fid=" + id3 + "&level=" + 4, ScreenActivity.this, mQueue);
        SharedPreferences preferences3 = this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
        String id4 = preferences3.getString("id4", null);
        //Log.e("=====", "id4=" + id4);
        //模块
        VolleyUtils.connectionGET4(VideoURL.MOKUAI + "fid=" + id4, ScreenActivity.this, mQueue);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_screen:
                bundle.putString("aaa", "back");//添加要返回给页面1的数据
                intent.putExtras(bundle);
                this.setResult(Activity.RESULT_OK, intent);//返回页面1
                this.finish();

                break;
            case R.id.btn_screen:
                SharedPreferences preferences3 = this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
                String id4 = preferences3.getString("id4", null);
                SharedPreferences preferences4 = ScreenActivity.this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
                String id5 = preferences4.getString("id5", null);
                SharedPreferences preferences5 = ScreenActivity.this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
                String type = preferences5.getString("type", null);
                bundle.putString("aaa", "back");
                bundle.putString("id4", id4);
                bundle.putString("id5", id5);
                bundle.putString("type", type);
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
            case R.id.gridview_1:
                getGridview1(position);
                break;
            //年级
            case R.id.gridview_2:
                getGridView2(position);
                break;
            //科目
            case R.id.gridview_3:
                getGridView3(position);
                break;
            //版本
            case R.id.gridview_4:
                getGridView4(position);
                break;
            //模块
            case R.id.gridview_5:
                getGridView5(position);
                break;
            //资源分类
            case R.id.gridview_6:
                getGridView6(position);
                break;
        }
    }

    //学段
    @Override
    public void getRegister(String message) {
        //Log.e("-----", message.toString());
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

    private void getGridview1(int position) {
        HashMap<String, String> hashMap = mList.get(position);
        String id1 = hashMap.get("id");
        // Log.e("abc", "学段id1=" + id1 + "");
        SharedPreferences preferences = ScreenActivity.this.getSharedPreferences("baby", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("id1", id1);
        edit.commit();
        VolleyUtils.connectionGET1(VideoURL.JICHUFENLEI + "fid=" + id1 + "&level=" + 2, ScreenActivity.this, mQueue);
    }

    //年级
    @Override
    public void getRegister1(String message) {
        //  Log.e("1", message + "");
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

    private void getGridView2(int position) {
        HashMap<String, String> hashMap1 = mList1.get(position);
        String id2 = hashMap1.get("id");
        // Log.e("abc", "年级id2=" + id2 + "");
        SharedPreferences preferences1 = ScreenActivity.this.getSharedPreferences("baby1", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit1 = preferences1.edit();
        edit1.putString("id2", id2);
        edit1.commit();
        VolleyUtils.connectionGET2(VideoURL.JICHUFENLEI + "fid=" + id2 + "&level=" + 3, ScreenActivity.this, mQueue);
    }

    //科目
    @Override
    public void getRegister2(String message) {
        //  Log.e("2", message + "");
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

    private void getGridView3(int position) {
        HashMap<String, String> hashMap2 = mList2.get(position);
        String id3 = hashMap2.get("id");
        // Log.e("abc", "科目id3=" + id3 + "");
        SharedPreferences preferences2 = ScreenActivity.this.getSharedPreferences("baby2", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit2 = preferences2.edit();
        edit2.putString("id3", id3);
        edit2.commit();
        VolleyUtils.connectionGET3(VideoURL.JICHUFENLEI + "fid=" + id3 + "&level=" + 4, ScreenActivity.this, mQueue);
    }

    //版本
    @Override
    public void getRegister3(String message) {
        // Log.e("3", message + "");
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

    private void getGridView4(int position) {
        HashMap<String, String> hashMap3 = mList3.get(position);
        String id4 = hashMap3.get("id");
        // Log.e("abc", "版本id4=" + id4 + "");
        SharedPreferences preferences3 = ScreenActivity.this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit3 = preferences3.edit();
        edit3.putString("id4", id4);
        edit3.commit();
        VolleyUtils.connectionGET4(VideoURL.MOKUAI + "fid=" + id4, ScreenActivity.this, mQueue);
    }

    //模块
    @Override
    public void getRegister4(String message) {
        //Log.e("4", message + "");
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

    private void getGridView5(int position) {
        HashMap<String, String> hashMap4 = mList4.get(position);
        String id5 = hashMap4.get("id");
        // Log.e("abc", "模块id5=" + id5 + "");
        SharedPreferences preferences4 = ScreenActivity.this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit4 = preferences4.edit();
        edit4.putString("id5", id5);
        edit4.commit();
    }

    //资源分类
    @Override
    public void getRegister5(String message) {
        // Log.e("5", message + "");
        try {
            JSONObject jsonObject = new JSONObject(message);
            JSONArray data = jsonObject.getJSONArray("data");

            mList5.clear();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject1 = data.getJSONObject(i);
                String type = jsonObject1.getString("type");
                String name = jsonObject1.getString("name");
                map = new HashMap<>();
                map.put("type", type);
                map.put("name", name);
                mList5.add((HashMap<String, String>) map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ScreenAdapter(this);
        adapter.getEvent(mList5);
        mGridView6.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getGridView6(int position) {
        HashMap<String, String> hashMap5 = mList5.get(position);
        String type = hashMap5.get("type");
        // Log.e("abc", "资源type=" + type + "");
        SharedPreferences preferences5 = ScreenActivity.this.getSharedPreferences("baby3", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit5 = preferences5.edit();
        edit5.putString("type", type);
        edit5.commit();
    }


}
