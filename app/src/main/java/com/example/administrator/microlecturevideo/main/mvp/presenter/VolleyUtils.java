package com.example.administrator.microlecturevideo.main.mvp.presenter;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener1;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener2;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener3;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener4;
import com.example.administrator.microlecturevideo.main.mvp.activity.weikevideo.inter.OnLoadRegisterListener5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */

public class VolleyUtils {

    //get方法
    public  static void connectionGET(String uri, final OnLoadRegisterListener onLoadRegisterListener, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener.getRegister(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Log.e("=====",error.toString());
            }
        });
        requestQueue.add(request);
    }
    //get方法
    public static void connectionGET1(String uri, final OnLoadRegisterListener1 onLoadRegisterListener1, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener1.getRegister1(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "" + error.getMessage());
            }
        });
        requestQueue.add(request);

    }
    //get方法
    public static void connectionGET2(String uri, final OnLoadRegisterListener2 onLoadRegisterListener2, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener2.getRegister2(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "" + error.getMessage());
            }
        });
        requestQueue.add(request);

    }
    //get方法
    public static void connectionGET3(String uri, final OnLoadRegisterListener3 onLoadRegisterListener3, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener3.getRegister3(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "" + error.getMessage());
            }
        });
        requestQueue.add(request);

    }
    //get方法
    public static void connectionGET4(String uri, final OnLoadRegisterListener4 onLoadRegisterListener4, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener4.getRegister4(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "" + error.getMessage());
            }
        });
        requestQueue.add(request);

    }
    //get方法
    public static void connectionGET5(String uri, final OnLoadRegisterListener5 onLoadRegisterListener5, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener5.getRegister5(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----", "" + error.getMessage());
            }
        });
        requestQueue.add(request);

    }
    //post方法
    public static void connectionPOST(String uri, final String name, final String password, final OnLoadRegisterListener onLoadRegisterListener, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.POST, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoadRegisterListener.getRegister(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("-----", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("ver", "1");
                map.put("uid", name);
                map.put("pwd", password);
                map.put("device", "0");
                return map;
            }
        };
        requestQueue.add(request);
    }
}