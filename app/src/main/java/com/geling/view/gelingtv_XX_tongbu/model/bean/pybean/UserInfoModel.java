package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/3/2------更新------
 * 用户信息
 */

public class UserInfoModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"uid":3461,"phone":"15913107564","wechatnickname":null,"openid":null,"sex":0,"headimgurl":null,"token":"a091c6ae16dc50ac3bc9cac1ce7cf70b","isVip":0,"vip_time":"1970-01-01 08:00:00"}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private Databean data;

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
         * uid : 3461
         * phone : 15913107564
         * wechatnickname : null
         * openid : null
         * sex : 0
         * headimgurl : null
         * token : a091c6ae16dc50ac3bc9cac1ce7cf70b
         * isVip : 0
         * vip_time : 1970-01-01 08:00:00
         */

        private int uid;
        private String phone;
        private Object wechatnickname;
        private Object openid;
        private int sex;
        private Object headimgurl;
        private String token;
        private int isVip;//会员：0非会员，1会员
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

        public Object getWechatnickname() {
            return wechatnickname;
        }

        public void setWechatnickname(Object wechatnickname) {
            this.wechatnickname = wechatnickname;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
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
