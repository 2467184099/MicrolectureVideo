package com.example.administrator.microlecturevideo.main.mvp.model;

/**
 * Created by Administrator on 2017/4/26.
 */

public class Obj {
    /**
     * code : 0
     * info : 查询成功
     * data : {"list":[{"id":"2769","name":"微素材·点亮作文","price":"20.00","clanum":"40","pic":"http://www.91taoke.com/images/photo/1440730460.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"2759","name":"高一语文聆听美文2 人教版","price":"10.00","clanum":"65","pic":"http://www.91taoke.com/images/photo/1440729843.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"1185","name":"高一语文上（必修1、2）  人教版古诗文 ","price":"80.00","clanum":"31","pic":"http://www.91taoke.com/images/photo/1404436094.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"1183","name":"高一语文下（必修3、4）  人教版古诗文","price":"80.00","clanum":"30","pic":"http://www.91taoke.com/images/photo/1404436107.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"171","name":"高一语文聆听美文 人教版","price":"10.00","clanum":"76","pic":"http://www.91taoke.com/images/photo/1483406859.jpg","type":"1","kecheng_id":"1001021","payly":1},{"id":"163","name":"高一语文特色专题课程 人教版","price":"10.00","clanum":"15","pic":"http://www.91taoke.com/images/photo/1403313839.png","type":"1","kecheng_id":"1001021","payly":1},{"id":"143","name":"高一语文上学期期末备考课程 人教版","price":"30.00","clanum":"12","pic":"http://www.91taoke.com/images/photo/1403310687.png","type":"2","kecheng_id":"1001021","payly":1},{"id":"135","name":"高一语文上学期期中备考课程 人教版","price":"40.00","clanum":"13","pic":"http://www.91taoke.com/images/photo/1403310150.png","type":"2","kecheng_id":"1001021","payly":1},{"id":"65","name":"高一语文重难点突破课程 人教版","price":"60.00","clanum":"28","pic":"http://www.91taoke.com/images/photo/1403253776.png","type":"1","kecheng_id":"1001021","payly":1}],"totalPage":1}
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
}
