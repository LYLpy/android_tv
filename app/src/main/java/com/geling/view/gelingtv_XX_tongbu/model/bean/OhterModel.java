package com.geling.view.gelingtv_XX_tongbu.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/19------更新-----
 * 其他数据的bean
 */

public class OhterModel {

    /**
     * code : 10000
     * msg : 请求成功
     * data : {"logo":"http://iptv3.com/Public/Uploads/images/2019-11-19/5dd3af01910c2.png","open_status":"1","open_time":"5","open_img":"http://iptv3.com/Public/Uploads/images/2019-11-04/timg.jpg"}
     */

    private int code;
    private String msg;
    private Databean data;

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

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * logo : http://iptv3.com/Public/Uploads/images/2019-11-19/5dd3af01910c2.png
         * open_status : 1
         * open_time : 5
         * open_img : http://iptv3.com/Public/Uploads/images/2019-11-04/timg.jpg
         */

        private String logo;
        private String open_status;
        private String open_time;
        private String open_img;
        private String vip_text;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getOpen_status() {
            return open_status;
        }

        public void setOpen_status(String open_status) {
            this.open_status = open_status;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
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
    }
}
