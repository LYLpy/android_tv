package com.geling.view.gelingtv_XX_tongbu.model.bean;

import com.google.gson.Gson;

public class OhterModel1 {


    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"id":1,"project_name":"同步培优tv","logo":"http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2019-12-30/5e09b7c96be59.png","open_status":1,"open_time":5,"open_img":"http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2020-01-08/5e1595b41ab8c.jpg","vip_text":"格灵VIP·开通|时限优惠！仅39元/月","home_img":"/Public/Uploads/images/2020-01-10/5e1874b484566.jpg","button_color":"#dc4e48","button_color_focus":"#efc718","button_font_color":"#ffffff","button_font_color_focus":"#a10000","button_font_color_selected":"#f5d71a","button_bgimg_home_top_nav":"","pay_prompt":"温馨提示：扫码订购前请确保基本收视维护费正常，客服热线96123。","customer_service_code":"http://test-xxtbpy.getlearn.cn/uploads/20200221/30a03101519328580b17c83a29e5f7cb.png","app_code":"http://test-xxtbpy.getlearn.cn/uploads/20200221/30a03101519328580b17c83a29e5f7cb.png","coupon_background":"http://test-xxtbpy.getlearn.cn/uploads/20200221/bb7c6afd26c41ae63ba5fc045d840d99.png"}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private Databean data;

    public static OhterModel1 gsonBean(String json){
        return new Gson().fromJson(json,OhterModel1.class);
    }

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

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * id : 1
         * project_name : 同步培优tv
         * logo : http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2019-12-30/5e09b7c96be59.png
         * open_status : 1
         * open_time : 5
         * open_img : http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2020-01-08/5e1595b41ab8c.jpg
         * vip_text : 格灵VIP·开通|时限优惠！仅39元/月
         * home_img : /Public/Uploads/images/2020-01-10/5e1874b484566.jpg
         * button_color : #dc4e48
         * button_color_focus : #efc718
         * button_font_color : #ffffff
         * button_font_color_focus : #a10000
         * button_font_color_selected : #f5d71a
         * button_bgimg_home_top_nav :
         * pay_prompt : 温馨提示：扫码订购前请确保基本收视维护费正常，客服热线96123。
         * customer_service_code : http://test-xxtbpy.getlearn.cn/uploads/20200221/30a03101519328580b17c83a29e5f7cb.png
         * app_code : http://test-xxtbpy.getlearn.cn/uploads/20200221/30a03101519328580b17c83a29e5f7cb.png
         * coupon_background : http://test-xxtbpy.getlearn.cn/uploads/20200221/bb7c6afd26c41ae63ba5fc045d840d99.png
         */

        private int id;
        private String project_name;
        private String logo;
        private int open_status;
        private int open_time;
        private String open_img;
        private String vip_text;
        private String home_img;
        private String button_color;
        private String button_color_focus;
        private String button_font_color;
        private String button_font_color_focus;
        private String button_font_color_selected;
        private String button_bgimg_home_top_nav;
        private String pay_prompt;
        private String customer_service_code;
        private String app_code;
        private String coupon_background;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getOpen_status() {
            return open_status;
        }

        public void setOpen_status(int open_status) {
            this.open_status = open_status;
        }

        public int getOpen_time() {
            return open_time;
        }

        public void setOpen_time(int open_time) {
            this.open_time = open_time;
        }

        public String getOpen_img() {
            return open_img;
        }

        public void setOpen_img(String open_img) {
            this.open_img = open_img;
        }

        public String getVip_text() {
            return vip_text;
        }

        public void setVip_text(String vip_text) {
            this.vip_text = vip_text;
        }

        public String getHome_img() {
            return home_img;
        }

        public void setHome_img(String home_img) {
            this.home_img = home_img;
        }

        public String getButton_color() {
            return button_color;
        }

        public void setButton_color(String button_color) {
            this.button_color = button_color;
        }

        public String getButton_color_focus() {
            return button_color_focus;
        }

        public void setButton_color_focus(String button_color_focus) {
            this.button_color_focus = button_color_focus;
        }

        public String getButton_font_color() {
            return button_font_color;
        }

        public void setButton_font_color(String button_font_color) {
            this.button_font_color = button_font_color;
        }

        public String getButton_font_color_focus() {
            return button_font_color_focus;
        }

        public void setButton_font_color_focus(String button_font_color_focus) {
            this.button_font_color_focus = button_font_color_focus;
        }

        public String getButton_font_color_selected() {
            return button_font_color_selected;
        }

        public void setButton_font_color_selected(String button_font_color_selected) {
            this.button_font_color_selected = button_font_color_selected;
        }

        public String getButton_bgimg_home_top_nav() {
            return button_bgimg_home_top_nav;
        }

        public void setButton_bgimg_home_top_nav(String button_bgimg_home_top_nav) {
            this.button_bgimg_home_top_nav = button_bgimg_home_top_nav;
        }

        public String getPay_prompt() {
            return pay_prompt;
        }

        public void setPay_prompt(String pay_prompt) {
            this.pay_prompt = pay_prompt;
        }

        public String getCustomer_service_code() {
            return customer_service_code;
        }

        public void setCustomer_service_code(String customer_service_code) {
            this.customer_service_code = customer_service_code;
        }

        public String getApp_code() {
            return app_code;
        }

        public void setApp_code(String app_code) {
            this.app_code = app_code;
        }

        public String getCoupon_background() {
            return coupon_background;
        }

        public void setCoupon_background(String coupon_background) {
            this.coupon_background = coupon_background;
        }
    }
}
