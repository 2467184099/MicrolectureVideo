package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.adapter.ExerciseWebAdapter;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener1;
import com.example.administrator.microlecturevideo.main.mvp.model.DataBean1;
import com.example.administrator.microlecturevideo.main.mvp.model.ExerciseWebInfo;
import com.example.administrator.microlecturevideo.main.mvp.model.VideoURL;
import com.example.administrator.microlecturevideo.main.mvp.presenter.VolleyUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.administrator.microlecturevideo.main.mvp.presenter.PixelUtils.mContext;


/**
 * 习题微课章节列表
 */
public class ExerciseWebActivity extends AppCompatActivity implements OnLoadRegisterListener, ExpandableListView.OnChildClickListener, OnLoadRegisterListener1 {
    private ImageView mImage_back;
    private TextView mText_buy;
    ExpandableListView mExpandableListView;
    Bundle bundle;
    Intent intent;
    static List<DataBean1> data;
    String id1;
    String name;
    int payly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_web);
        getString();
        //初始化UI布局
        initView();
        RequestQueue queue = Volley.newRequestQueue(this);
        VolleyUtils.connectionGET(VideoURL.ZHANGJIELIEBIAO + "subjectId=" + id1, this, queue);

    }

    private void getString() {
        intent = this.getIntent();
        bundle = intent.getExtras();
        Intent intent = this.getIntent();
        id1 = intent.getStringExtra("id1");
        Log.e("eeeeee","id1="+id1);
        name = intent.getStringExtra("name");
        payly = intent.getIntExtra("payly", 0);
    }

    //接受的参数


    //初始化UI布局
    private void initView() {
        mImage_back = (ImageView) findViewById(R.id.execrise_web_img);
        mText_buy = (TextView) findViewById(R.id.execrise_web_buy);
        if (payly == 1||payly==0) {
            mText_buy.setText("已购买");
        } else if (payly == 2) {
            mText_buy.setText("购买");
            if (mText_buy.getText().equals("购买")) {
                mText_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(ExerciseWebActivity.this);
                        dialog.setMessage("是否购买《" + name + "》");
                        dialog.setTitle("提示");
                        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                RequestQueue queue = Volley.newRequestQueue(ExerciseWebActivity.this);
                                String url = VideoURL.GOUMAI + "&uid=" + 11945763 + "&tid=" + id1;
                                VolleyUtils.connectionGET1(url, ExerciseWebActivity.this, queue);
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
                });
            }
        }

        mExpandableListView = (ExpandableListView) findViewById(R.id.execrise_web_list);
        mExpandableListView.setOnChildClickListener(this);
        mImage_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("bbb", "back");//添加要返回给页面1的数据
                intent.putExtras(bundle);
                ExerciseWebActivity.this.setResult(Activity.RESULT_OK, intent);//返回页面1
                ExerciseWebActivity.this.finish();
            }
        });
    }


    //数据处理
    @Override
    public void getRegister(String message) {
        //Log.e("------", message.toString());
        Gson gson = new Gson();
        ExerciseWebInfo exerciseWebInfo = gson.fromJson(message, ExerciseWebInfo.class);
        int code = exerciseWebInfo.getCode();
        if (code == 0) {
            data = exerciseWebInfo.getData();
            ExerciseWebAdapter exerciseWebAdapter = new ExerciseWebAdapter(this, data);
            mExpandableListView.setAdapter(exerciseWebAdapter);
            exerciseWebAdapter.notifyDataSetChanged();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("暂无数据，请重新加载");
            dialog.setTitle("提示");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
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


    //内层点击事件
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
      if (mText_buy.getText().equals("已购买")){
          Intent intent = new Intent(ExerciseWebActivity.this, ExerciseWebListActivity.class);
          intent.putExtra("groupPosition", groupPosition);
          intent.putExtra("childPosition", childPosition);
          startActivity(intent);
      }else {
          Toast.makeText(ExerciseWebActivity.this,"请购买后在操作",Toast.LENGTH_SHORT).show();
      }


        return true;
    }


    //数据调用
    public static List<DataBean1> getList() {
        return data;
    }


    @Override
    public void getRegister1(String message) {
        Log.e("======", message.toString());
        try {
            JSONObject jsonObject = new JSONObject(message);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                Toast.makeText(ExerciseWebActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                mText_buy.setText("已购买");
            } else if (code == 1) {
                Toast.makeText(mContext, "该配套课时已购买过，无需再次购买", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
