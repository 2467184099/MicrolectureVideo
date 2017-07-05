package com.example.administrator.microlecturevideo.main.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ExerciseWebInfo {

    /**
     * code : 0
     * info : 查询成功
     * data : [{"id":"204505","fid":"1000637","name":"第一章 集合与函数概念","level":1,"mokuai_id":"1000637","is_free":"2","childList":[{"id":"204509","fid":"204505","name":" 1.1.1 第1课时 集合的含义","level":2,"mokuai_id":null,"is_free":"1"},{"id":"207305","fid":"204505","name":" 1.1.1 第2课时 集合的表示","level":2,"mokuai_id":null,"is_free":"1"},{"id":"204511","fid":"204505","name":"1.1.2 集合间的基本关系","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204513","fid":"204505","name":"1.1.3 第1课时 并集与交集 ","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207409","fid":"204505","name":"1.1.3 第2课时 补集及综合应用","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204515","fid":"204505","name":"习题课 集合的概念与运算","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204517","fid":"204505","name":"1.2.1 函数的概念","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204519","fid":"204505","name":"1.2.2 第1课时 函数的表示法","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207411","fid":"204505","name":"1.2.2 第2课时 分段函数及映射","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204521","fid":"204505","name":"1.3.1 第1课时 函数的单调性","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207417","fid":"204505","name":"1.3.1 第2课时 函数的最大(小)值 ","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204523","fid":"204505","name":"1.3.2 第1课时 奇偶性的概念","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207413","fid":"204505","name":"1.3.2 第2课时 奇偶性的应用","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204525","fid":"204505","name":"章末复习课","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204527","fid":"204505","name":"章末检测","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204563","fid":"204505","name":"综合检测卷","level":2,"mokuai_id":null,"is_free":"2"}]},{"id":"204529","fid":"1000637","name":"第二章 基本初等函数(Ⅰ)","level":1,"mokuai_id":"1000637","is_free":"2","childList":[{"id":"204531","fid":"204529","name":"2.1.1 指数与指数幂的运算","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204533","fid":"204529","name":"2.1.2 指数函数及其性质","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204535","fid":"204529","name":"2.2.1 第1课时 对 数 ","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207419","fid":"204529","name":"2.2.1 第2课时 对数的运算","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204537","fid":"204529","name":"2.2.2 对数函数及其性质","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204539","fid":"204529","name":"习题课 对数函数","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204541","fid":"204529","name":"§2.3 幂函数","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204543","fid":"204529","name":"章末复习课","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204545","fid":"204529","name":"章末检测","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204565","fid":"204529","name":"综合检测卷","level":2,"mokuai_id":null,"is_free":"2"}]},{"id":"204547","fid":"1000637","name":"第三章 函数的应用","level":1,"mokuai_id":"1000637","is_free":"2","childList":[{"id":"204549","fid":"204547","name":"3.1.1 方程的根与函数的零点","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204551","fid":"204547","name":"3.1.2 用二分法求方程的近似解","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204553","fid":"204547","name":"3.2.1 几类不同增长的函数模型","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204555","fid":"204547","name":"3.2.2 函数模型的应用实例","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204557","fid":"204547","name":"习题课 函数的实际应用","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204559","fid":"204547","name":"章末复习课","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204561","fid":"204547","name":"章末检测","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204567","fid":"204547","name":"综合检测卷","level":2,"mokuai_id":null,"is_free":"2"}]}]
     */

    private int code;
    private String info;
    private List<DataBean1> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean1> getData() {
        return data;
    }

    public void setData(List<DataBean1> data) {
        this.data = data;
    }


}

