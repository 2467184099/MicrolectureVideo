package com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.microlecturevideo.R;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.Screen2Activity;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.activity.ScreenActivity;

/**
 * 微课视频和习题微课
 */

public class VideoFragment extends Fragment {
    FrameLayout mFramentLayout;
    private TextView mText_microlecture, getmText_exercise;
    private ImageView mImg_left, mImg_screen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myvideo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化
        initView(view);
        getData(0);
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void initView(View view) {
        mFramentLayout = (FrameLayout) view.findViewById(R.id.framelayout_video);
        mText_microlecture = (TextView) view.findViewById(R.id.txt_video);
        getmText_exercise = (TextView) view.findViewById(R.id.txt_course);
        mImg_left = (ImageView) view.findViewById(R.id.img_left);
        mImg_screen = (ImageView) view.findViewById(R.id.img_right);
        //碎片替换
        getFragment();
    }


    /**
     * 碎片替换
     */
    private void getFragment() {

        mText_microlecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(0);
            }
        });
        getmText_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(1);
            }
        });

    }

    public void getData(final int index) {
        switch (index) {
            case 0:
                mText_microlecture.setTextColor(Color.GREEN);
                mText_microlecture.setBackgroundColor(Color.WHITE);
                getmText_exercise.setTextColor(Color.WHITE);
                getmText_exercise.setBackgroundResource(R.color.green);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.framelayout_video, new MicrolectureFragment());
                transaction.commit();
                mImg_screen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), ScreenActivity.class);
                        Bundle bundle = new Bundle();
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 0);
                    }
                });
                break;
            case 1:
                getmText_exercise.setTextColor(Color.GREEN);
                getmText_exercise.setBackgroundColor(Color.WHITE);
                mText_microlecture.setTextColor(Color.WHITE);
                mText_microlecture.setBackgroundResource(R.color.green);
                FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.framelayout_video, new ExerciseFragment());
                transaction1.commit();
                mImg_screen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), Screen2Activity.class);
                        Bundle bundle = new Bundle();
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 1);
                    }
                });
                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String aaa = bundle.getString("aaa");
            String id5 = bundle.getString("id5");
            String id4 = bundle.getString("id4");
            String type = bundle.getString("type");
            Log.e("=====", "id5=" + id5 + "id4=" + id4 + "type=" + type);
            SharedPreferences shar = getActivity().getSharedPreferences("coustom", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = shar.edit();
            edit.putString("type", type);
            edit.putString("id5", id5);
            edit.putString("id4", id4);
            edit.commit();
            getData(0);
        }
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String bbb = bundle.getString("bbb");
            String id4 = bundle.getString("id4");
            String id5 = bundle.getString("id5");
            SharedPreferences shar = getActivity().getSharedPreferences("123", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = shar.edit();
            edit.putString("id5", id5);
            edit.putString("id4", id4);
            edit.commit();
            getData(1);
        }
    }
}
