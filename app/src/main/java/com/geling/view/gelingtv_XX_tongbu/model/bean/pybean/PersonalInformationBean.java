package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

public class PersonalInformationBean {
    public static PersonalInformationBean gsonBean(String json){
        return new Gson().fromJson(json,PersonalInformationBean.class);
    }
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"uid":3500,"phone":"17666558104","wechatnickname":"17666558104","openid":"17666558104","sex":0,"headimgurl":"17666558104","token":"0d427465b0d88ab870f951bb2c546094","isVip":0,"vip_time":"1970-01-01 08:00:00"}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 3500
         * phone : 17666558104
         * wechatnickname : 17666558104
         * openid : 17666558104
         * sex : 0
         * headimgurl : 17666558104
         * token : 0d427465b0d88ab870f951bb2c546094
         * isVip : 0
         * vip_time : 1970-01-01 08:00:00
         */

        private int uid;
        private String phone;
        private String wechatnickname;
        private String openid;
        private int sex;
        private String headimgurl;
        private String token;
        private int isVip;
        private String vip_time;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWechatnickname() {
            return wechatnickname;
        }

        public void setWechatnickname(String wechatnickname) {
            this.wechatnickname = wechatnickname;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIsVip() {
            return isVip;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public String getVip_time() {
            return vip_time;
        }

        public void setVip_time(String vip_time) {
            this.vip_time = vip_time;
        }
    }
}
