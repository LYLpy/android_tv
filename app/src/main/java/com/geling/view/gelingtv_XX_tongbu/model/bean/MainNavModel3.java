package com.geling.view.gelingtv_XX_tongbu.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/28------更新------
 * 主页导航栏数据
 */

public class MainNavModel3 {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"id":1,"nav_name":"首页","nav_type":1,"bind_id":0,"recommend_num":0,"is_show":1,"icon":"http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/1.png","selected_icon":"http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/1_s.png","focus_icon":"http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/1_f.png","index_module_id":2},{"id":2,"nav_name":"幼儿","nav_type":2,"bind_id":2,"recommend_num":8,"is_show":0,"icon":"http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/2.png","selected_icon":"http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/2_s.png","focus_icon":"http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/2_f.png"}]
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private List<Databean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
         * bind_id : 0
         * recommend_num : 0
         * is_show : 1
         * icon : http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/1.png
         * selected_icon : http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/1_s.png
         * focus_icon : http://images.gelearning.net/uploads//static/tv/images/homeNavIcon/1_f.png
         * index_module_id : 2
         */

        private int id;
        private String nav_name;
        private int nav_type;
        private int bind_id;
        private int recommend_num;
        private int is_show;
        private String icon;
        private String selected_icon;
        private String focus_icon;
        private int index_module_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNav_name() {
            return nav_name;
        }

        public void setNav_name(String nav_name) {
            this.nav_name = nav_name;
        }

        public int getNav_type() {
            return nav_type;
        }

        public void setNav_type(int nav_type) {
            this.nav_type = nav_type;
        }

        public int getBind_id() {
            return bind_id;
        }

        public void setBind_id(int bind_id) {
            this.bind_id = bind_id;
        }

        public int getRecommend_num() {
            return recommend_num;
        }

        public void setRecommend_num(int recommend_num) {
            this.recommend_num = recommend_num;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSelected_icon() {
            return selected_icon;
        }

        public void setSelected_icon(String selected_icon) {
            this.selected_icon = selected_icon;
        }

        public String getFocus_icon() {
            return focus_icon;
        }

        public void setFocus_icon(String focus_icon) {
            this.focus_icon = focus_icon;
        }

        public int getIndex_module_id() {
            return index_module_id;
        }

        public void setIndex_module_id(int index_module_id) {
            this.index_module_id = index_module_id;
        }
    }
}
