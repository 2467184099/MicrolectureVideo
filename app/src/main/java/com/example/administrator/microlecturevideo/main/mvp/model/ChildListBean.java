package com.example.administrator.microlecturevideo.main.mvp.model;

/**
 * Created by Administrator on 2017/5/8.
 */

public class ChildListBean {

        /**
         * id : 204509
         * fid : 204505
         * name :  1.1.1 第1课时 集合的含义
         * level : 2
         * mokuai_id : null
         * is_free : 1
         */

        private String id;
        private String fid;
        private String name;
        private int level;
        private Object mokuai_id;
        private String is_free;

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

        public Object getMokuai_id() {
            return mokuai_id;
        }

        public void setMokuai_id(Object mokuai_id) {
            this.mokuai_id = mokuai_id;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }


}

