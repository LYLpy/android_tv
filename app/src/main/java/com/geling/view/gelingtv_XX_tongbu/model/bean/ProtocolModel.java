package com.geling.view.gelingtv_XX_tongbu.model.bean;

import com.google.gson.Gson;
/**
 * 用户协议和保密协议
 * */
public class ProtocolModel {

    public static ProtocolModel gonsBean(String json){
        return new Gson().fromJson(json,ProtocolModel.class);
    }

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"protocol":""}
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
         * protocol :
         */

        private String protocol;

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }
    }
}
