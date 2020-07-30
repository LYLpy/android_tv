package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/3/2------更新------
 *
 */

public class PersonModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"uid":3461,"headimgurl":null,"wechatnickname":null,"isVip":0,"integral":3,"signature":null,"study":0,"challenges":0,"sign":false}
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
         * headimgurl : null
         * wechatnickname : null
         * isVip : 0
         * integral : 3
         * signature : null
         * study : 0
         * challenges : 0
         * sign : false
         */

        private int uid;
        private Object headimgurl;
        private Object wechatnickname;
        private int isVip;
        private int integral;
        private Object signature;
        private int study;
        private int challenges;
        private boolean sign;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
            this.headimgurl = headimgurl;
        }

        public Object getWechatnickname() {
            return wechatnickname;
        }

        public void setWechatnickname(Object wechatnickname) {
            this.wechatnickname = wechatnickname;
        }

        public int getIsVip() {
            return isVip;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }

        public int getStudy() {
            return study;
        }

        public void setStudy(int study) {
            this.study = study;
        }

        public int getChallenges() {
            return challenges;
        }

        public void setChallenges(int challenges) {
            this.challenges = challenges;
        }

        public boolean isSign() {
            return sign;
        }

        public void setSign(boolean sign) {
            this.sign = sign;
        }
    }
}
