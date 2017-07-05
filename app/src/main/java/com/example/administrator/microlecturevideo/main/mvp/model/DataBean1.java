package com.example.administrator.microlecturevideo.main.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class DataBean1 {

        /**
         * id : 204505
         * fid : 1000637
         * name : 第一章 集合与函数概念
         * level : 1
         * mokuai_id : 1000637
         * is_free : 2
         * childList : [{"id":"204509","fid":"204505","name":" 1.1.1 第1课时 集合的含义","level":2,"mokuai_id":null,"is_free":"1"},{"id":"207305","fid":"204505","name":" 1.1.1 第2课时 集合的表示","level":2,"mokuai_id":null,"is_free":"1"},{"id":"204511","fid":"204505","name":"1.1.2 集合间的基本关系","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204513","fid":"204505","name":"1.1.3 第1课时 并集与交集 ","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207409","fid":"204505","name":"1.1.3 第2课时 补集及综合应用","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204515","fid":"204505","name":"习题课 集合的概念与运算","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204517","fid":"204505","name":"1.2.1 函数的概念","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204519","fid":"204505","name":"1.2.2 第1课时 函数的表示法","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207411","fid":"204505","name":"1.2.2 第2课时 分段函数及映射","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204521","fid":"204505","name":"1.3.1 第1课时 函数的单调性","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207417","fid":"204505","name":"1.3.1 第2课时 函数的最大(小)值 ","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204523","fid":"204505","name":"1.3.2 第1课时 奇偶性的概念","level":2,"mokuai_id":null,"is_free":"2"},{"id":"207413","fid":"204505","name":"1.3.2 第2课时 奇偶性的应用","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204525","fid":"204505","name":"章末复习课","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204527","fid":"204505","name":"章末检测","level":2,"mokuai_id":null,"is_free":"2"},{"id":"204563","fid":"204505","name":"综合检测卷","level":2,"mokuai_id":null,"is_free":"2"}]
         */

        private String id;
        private String fid;
        private String name;
        private int level;
        private String mokuai_id;
        private String is_free;
        private List<ChildListBean> childList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getMokuai_id() {
            return mokuai_id;
        }

        public void setMokuai_id(String mokuai_id) {
            this.mokuai_id = mokuai_id;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }

        public List<ChildListBean> getChildList() {
            return childList;
        }

        public void setChildList(List<ChildListBean> childList) {
            this.childList = childList;
        }




}
