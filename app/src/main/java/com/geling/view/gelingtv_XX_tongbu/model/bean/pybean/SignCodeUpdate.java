package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import android.util.Log;

import com.geling.view.gelingtv_XX_tongbu.utils.LogUtil;
import com.google.gson.Gson;

public class SignCodeUpdate {


    public static SignCodeUpdate gsonBean(String json){
        LogUtil.e("修改SignCodeUpdate");
        return  new Gson().fromJson(json,SignCodeUpdate.class);

    }

    /**
     * msg : 手机号码更改成功!
     * data : {"user":{"country":null,"privileges":null,"unionid":null,"city":null,"signature":null,"channel":"","tokenTime":1590390401,"client_type":1,"qqopenid":null,"uid":66640,"password":null,"update_time":"2020-04-25 15:08:07","province":null,"figureurl":null,"integral":0,"reg_time":1587727353,"headimgurl":null,"invitationCode":null,"data_rem":"","wechatnickname":null,"openid":null,"sex":0,"vip_time":0,"phoneOld":"","wyt_uid":0,"isVip":0,"token":"9cc16f70369e5deb32dc87534cf18f68","phone":"13536834279","username":""}}
     * success : success
     * error : 10000
     * status : 200
     */
    private String msg;
    private DataEntity data;
    private String success;
    private int error;
    private String status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getSuccess() {
        return success;
    }

    public int getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }

    public class DataEntity {
        /**
         * user : {"country":null,"privileges":null,"unionid":null,"city":null,"signature":null,"channel":"","tokenTime":1590390401,"client_type":1,"qqopenid":null,"uid":66640,"password":null,"update_time":"2020-04-25 15:08:07","province":null,"figureurl":null,"integral":0,"reg_time":1587727353,"headimgurl":null,"invitationCode":null,"data_rem":"","wechatnickname":null,"openid":null,"sex":0,"vip_time":0,"phoneOld":"","wyt_uid":0,"isVip":0,"token":"9cc16f70369e5deb32dc87534cf18f68","phone":"13536834279","username":""}
         */
        private UserEntity user;

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public UserEntity getUser() {
            return user;
        }

        public class UserEntity {
            /**
             * country : null
             * privileges : null
             * unionid : null
             * city : null
             * signature : null
             * channel :
             * tokenTime : 1590390401
             * client_type : 1
             * qqopenid : null
             * uid : 66640
             * password : null
             * update_time : 2020-04-25 15:08:07
             * province : null
             * figureurl : null
             * integral : 0
             * reg_time : 1587727353
             * headimgurl : null
             * invitationCode : null
             * data_rem :
             * wechatnickname : null
             * openid : null
             * sex : 0
             * vip_time : 0
             * phoneOld :
             * wyt_uid : 0
             * isVip : 0
             * token : 9cc16f70369e5deb32dc87534cf18f68
             * phone : 13536834279
             * username :
             */
            private String country;
            private String privileges;
            private String unionid;
            private String city;
            private String signature;
            private String channel;
            private int tokenTime;
            private int client_type;
            private String qqopenid;
            private int uid;
            private String password;
            private String update_time;
            private String province;
            private String figureurl;
            private int integral;
            private int reg_time;
            private String headimgurl;
            private String invitationCode;
            private String data_rem;
            private String wechatnickname;
            private String openid;
            private int sex;
            private int vip_time;
            private String phoneOld;
            private int wyt_uid;
            private int isVip;
            private String token;
            private String phone;
            private String username;

            public void setCountry(String country) {
                this.country = country;
            }

            public void setPrivileges(String privileges) {
                this.privileges = privileges;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public void setTokenTime(int tokenTime) {
                this.tokenTime = tokenTime;
            }

            public void setClient_type(int client_type) {
                this.client_type = client_type;
            }

            public void setQqopenid(String qqopenid) {
                this.qqopenid = qqopenid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public void setFigureurl(String figureurl) {
                this.figureurl = figureurl;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public void setReg_time(int reg_time) {
                this.reg_time = reg_time;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }

            public void setData_rem(String data_rem) {
                this.data_rem = data_rem;
            }

            public void setWechatnickname(String wechatnickname) {
                this.wechatnickname = wechatnickname;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public void setVip_time(int vip_time) {
                this.vip_time = vip_time;
            }

            public void setPhoneOld(String phoneOld) {
                this.phoneOld = phoneOld;
            }

            public void setWyt_uid(int wyt_uid) {
                this.wyt_uid = wyt_uid;
            }

            public void setIsVip(int isVip) {
                this.isVip = isVip;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getCountry() {
                return country;
            }

            public String getPrivileges() {
                return privileges;
            }

            public String getUnionid() {
                return unionid;
            }

            public String getCity() {
                return city;
            }

            public String getSignature() {
                return signature;
            }

            public String getChannel() {
                return channel;
            }

            public int getTokenTime() {
                return tokenTime;
            }

            public int getClient_type() {
                return client_type;
            }

            public String getQqopenid() {
                return qqopenid;
            }

            public int getUid() {
                return uid;
            }

            public String getPassword() {
                return password;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public String getProvince() {
                return province;
            }

            public String getFigureurl() {
                return figureurl;
            }

            public int getIntegral() {
                return integral;
            }

            public int getReg_time() {
                return reg_time;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public String getInvitationCode() {
                return invitationCode;
            }

            public String getData_rem() {
                return data_rem;
            }

            public String getWechatnickname() {
                return wechatnickname;
            }

            public String getOpenid() {
                return openid;
            }

            public int getSex() {
                return sex;
            }

            public int getVip_time() {
                return vip_time;
            }

            public String getPhoneOld() {
                return phoneOld;
            }

            public int getWyt_uid() {
                return wyt_uid;
            }

            public int getIsVip() {
                return isVip;
            }

            public String getToken() {
                return token;
            }

            public String getPhone() {
                return phone;
            }

            public String getUsername() {
                return username;
            }
        }
    }
}
