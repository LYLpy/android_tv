package com.geling.view.gelingtv_XX_tongbu.model.bean;


import com.google.gson.Gson;

/**
 * 是有外语通的用户来获取到token
 */
public class UserTokenBean {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"token":"ba701690b6d61da44ac20e3f6dabdda9","uid":66629,"phone":null,"integral":0,"isVip":0,"vip_time":0,"reg_time":1585726490}
     */
    public static UserTokenBean gsonBean(String json) {
        return new Gson().fromJson(json, UserTokenBean.class);
    }
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
         * token : ba701690b6d61da44ac20e3f6dabdda9
         * uid : 66629
         * phone : null
         * integral : 0
         * isVip : 0
         * vip_time : 0
         * reg_time : 1585726490
         */

        private String token;
        private int uid;
        private Object phone;
        private int integral;
        private int isVip;
        private int vip_time;
        private int reg_time;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIsVip() {
            return isVip;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public int getVip_time() {
            return vip_time;
        }

        public void setVip_time(int vip_time) {
            this.vip_time = vip_time;
        }

        public int getReg_time() {
            return reg_time;
        }

        public void setReg_time(int reg_time) {
            this.reg_time = reg_time;
        }
    }
}
