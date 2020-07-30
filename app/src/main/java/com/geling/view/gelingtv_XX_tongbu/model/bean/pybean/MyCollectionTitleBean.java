package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

import java.util.List;

public class MyCollectionTitleBean {
    public static MyCollectionTitleBean gsonBean(String json) {
        return new Gson().fromJson(json, MyCollectionTitleBean.class);
    }
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"id":23,"option":"语文"},{"id":24,"option":"数学"},{"id":25,"option":"英语"}]
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public DataBean(int id, String option) {
            this.id = id;
            this.option = option;
        }

        /**
         * id : 23
         * option : 语文
         */

        private int id;
        private String option;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }
    }
}
