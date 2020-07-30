package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

public class SignVerificationCodeBean {


    public static SignVerificationCodeBean gsonBean(String json){
        return new Gson().fromJson(json,SignVerificationCodeBean.class);
    }
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"verify_id":"685b6f904acf8ac972a11b0ccf865f7c","is_old":0,"phone":"17666558104"}
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
         * verify_id : 685b6f904acf8ac972a11b0ccf865f7c
         * is_old : 0
         * phone : 17666558104
         */

        private String verify_id;
        private int is_old;
        private String phone;

        public String getVerify_id() {
            return verify_id;
        }

        public void setVerify_id(String verify_id) {
            this.verify_id = verify_id;
        }

        public int getIs_old() {
            return is_old;
        }

        public void setIs_old(int is_old) {
            this.is_old = is_old;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
