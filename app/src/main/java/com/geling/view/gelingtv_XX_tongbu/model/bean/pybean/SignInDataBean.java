package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

public class SignInDataBean {

    public static SignInDataBean gsonBean(String json){
        return new Gson().fromJson(json,SignInDataBean.class);
    }
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"uid":3500,"phone":"17666558104","wechatnickname":null,"openid":null,"sex":0,"province":null,"city":null,"country":null,"headimgurl":null,"privileges":null,"unionid":null,"token":"398ea01351af8f1d5cd43bc5e969092e","integral":0,"invitationCode":null,"isVip":0,"vip_time":0,"reg_time":1581919089,"select_course":{}}
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
         * wechatnickname : null
         * openid : null
         * sex : 0
         * province : null
         * city : null
         * country : null
         * headimgurl : null
         * privileges : null
         * unionid : null
         * token : 398ea01351af8f1d5cd43bc5e969092e
         * integral : 0
         * invitationCode : null
         * isVip : 0
         * vip_time : 0
         * reg_time : 1581919089
         * select_course : {}
         */

        private int uid;
        private String phone;
        private String wechatnickname;
        private String openid;
        private int sex;
        private String province;
        private String city;
        private String country;
        private String headimgurl;
        private String privileges;
        private String unionid;
        private String token;
        private int integral;
        private String invitationCode;
        private int isVip;
        private int vip_time;
        private int reg_time;

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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getPrivileges() {
            return privileges;
        }

        public void setPrivileges(String privileges) {
            this.privileges = privileges;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
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
