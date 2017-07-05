package com.example.administrator.microlecturevideo.main.mvp.model;

/**
 * Created by Administrator on 2017/5/3.
 */

public class VideoURL {
    public static final String  BASE_URL = "http://www.91taoke.com/";
    //基础分类
    public static final String  JICHUFENLEI = BASE_URL+"WeikeInterface/get_basic_class?";
    //模块分类
    public static final String  MOKUAI=BASE_URL+"WeikeInterface/get_mokuai?";
    //资源分类
    public static final String  ZIYUAN=BASE_URL+"WeikeInterface/get_data_type?";
    //微课视频课程列表
    public static final String  KECHENG=BASE_URL+"WeikeInterface/getList?";
    //微课视频/习题微课——购买课程
    public static final String  GOUMAI=BASE_URL+"WeikeInterface/pay_weike?";
    //微课视频-视频详情-免费课程列表
    public static final String  MIANFEI=BASE_URL+"WeikeInterface/getFreeView?";
    //微课视频-视频详情-收费课程列表
    public static final String  SHOUFEI=BASE_URL+"WeikeInterface/getChargeView?";
    //课视频-视频详情-收费课程-获取播放地址
    public static final String  BOFANG=BASE_URL+"WeikeInterface/getViewUrl?";
    //习题微课-课程详情-章节列表
    public static final String  ZHANGJIELIEBIAO=BASE_URL+"WeikeInterface/getXitiweikeClass?";
    //习题微课-课程详情-习题列表
    public static final String  XITILIEBIAO=BASE_URL+"WeikeInterface/getXitiweikeInfo?";
    //我的课程-课程列表
    public static final String  WODEKECHENG=BASE_URL+"WeikeInterface/getBuyList?";
}
