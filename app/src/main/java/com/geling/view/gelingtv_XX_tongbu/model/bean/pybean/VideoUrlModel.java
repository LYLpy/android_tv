package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/21------更新------
 * 视频地址bean
 */

public class VideoUrlModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"url":"https://gzgl.gelearning.net/zjywb/uploadfile/tongbushipin_xiaoxue/rjbxxyw1njsc201601.ts.mp4?auth_key=1582279142-8XQ67Pta00000000000000150000000000000002-00000000000000000000000000000000-a0cbb2256e638167ec561a346bed88d7","timestamp":1582279142}
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
         * url : https://gzgl.gelearning.net/zjywb/uploadfile/tongbushipin_xiaoxue/rjbxxyw1njsc201601.ts.mp4?auth_key=1582279142-8XQ67Pta00000000000000150000000000000002-00000000000000000000000000000000-a0cbb2256e638167ec561a346bed88d7
         * timestamp : 1582279142
         */

        private String url;
        private int timestamp;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}
