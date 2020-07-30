package com.geling.view.gelingtv_XX_tongbu.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/28------更新------
 * 主页导航栏数据
 */

public class MainNavModel2 {

    /**
     * code : 10000
     * msg : 请求成功
     * data : [{"id":"1","nav_name":"首页","nav_type":"1","class_name":"21","bind_id":"0","is_show":"1"},{"id":"2","nav_name":"幼儿","nav_type":"2","class_name":"qdd","bind_id":"5","is_show":"1"},{"id":"3","nav_name":"专题","nav_type":"2","class_name":"","bind_id":"5","is_show":"1"},{"id":"4","nav_name":"一年级","nav_type":"3","class_name":"","bind_id":"3","is_show":"1"},{"id":"5","nav_name":"二年级","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"6","nav_name":"三年级","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"7","nav_name":"四年级","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"8","nav_name":"五年级","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"9","nav_name":"六年级","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"10","nav_name":"初一","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"11","nav_name":"初二","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"12","nav_name":"初三","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"13","nav_name":"高一","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"14","nav_name":"高二","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"},{"id":"15","nav_name":"高三","nav_type":"3","class_name":"","bind_id":"0","is_show":"1"}]
     */

    private int code;
    private String msg;
    private List<Databean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Databean> getData() {
        return data;
    }

    public void setData(List<Databean> data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * id : 1
         * nav_name : 首页
         * nav_type : 1
         * class_name : 21
         * bind_id : 0
         * is_show : 1
         */

        private String id;
        private String nav_name;
        private String nav_type;
        private String class_name;
        private String bind_id;
        private String is_show;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNav_name() {
            return nav_name;
        }

        public void setNav_name(String nav_name) {
            this.nav_name = nav_name;
        }

        public String getNav_type() {
            return nav_type;
        }

        public void setNav_type(String nav_type) {
            this.nav_type = nav_type;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getBind_id() {
            return bind_id;
        }

        public void setBind_id(String bind_id) {
            this.bind_id = bind_id;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }
    }
}
