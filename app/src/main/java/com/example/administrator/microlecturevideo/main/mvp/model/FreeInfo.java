package com.example.administrator.microlecturevideo.main.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class FreeInfo {

    /**
     * code : 0
     * info : 查询成功
     * data : {"list":[{"id":"17774","name":"转身，留份美丽给自己","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/1zs.flv?st=XjmAJ78TGzeFSkBwna0-ZA&e=1493883555"]},{"id":"17775","name":"妈妈，你未曾和我好好告别","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/2mama.flv?st=2uIShgSpCTTud49Un1bN6A&e=1493883555"]},{"id":"17776","name":"仰望蓝色的天空","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/3yw.flv?st=rWcRIBmD_LTOMosoeA9rgw&e=1493883555"]},{"id":"17777","name":"当岁月开出执迷不悟的花","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/4hua.flv?st=yeyYpzPzEpfLBcfzjet3cA&e=1493883555"]},{"id":"17778","name":"爱是一场修行","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/5xiux.flv?st=EPc-tVHY5BU7YC6tkbdMBw&e=1493883555"]}],"totalPage":1}
     */

    private int code;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":"17774","name":"转身，留份美丽给自己","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/1zs.flv?st=XjmAJ78TGzeFSkBwna0-ZA&e=1493883555"]},{"id":"17775","name":"妈妈，你未曾和我好好告别","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/2mama.flv?st=2uIShgSpCTTud49Un1bN6A&e=1493883555"]},{"id":"17776","name":"仰望蓝色的天空","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/3yw.flv?st=rWcRIBmD_LTOMosoeA9rgw&e=1493883555"]},{"id":"17777","name":"当岁月开出执迷不悟的花","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/4hua.flv?st=yeyYpzPzEpfLBcfzjet3cA&e=1493883555"]},{"id":"17778","name":"爱是一场修行","urlArr":["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/5xiux.flv?st=EPc-tVHY5BU7YC6tkbdMBw&e=1493883555"]}]
         * totalPage : 1
         */

        private int totalPage;
        private List<ListBean> list;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 17774
             * name : 转身，留份美丽给自己
             * urlArr : ["http://beike.91taoke.com/file/yuwen/gzwk/ltmw/1zs.flv?st=XjmAJ78TGzeFSkBwna0-ZA&e=1493883555"]
             */

            private String id;
            private String name;
            private List<String> urlArr;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getUrlArr() {
                return urlArr;
            }

            public void setUrlArr(List<String> urlArr) {
                this.urlArr = urlArr;
            }
        }
    }
}
