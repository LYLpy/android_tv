package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

public class FreedbackModel {

    public static FreedbackModel gsonBean(String json){
        return new Gson().fromJson(json,FreedbackModel.class);

    }

    /**
     * msg : 请求成功
     * data : {"qrcodeUrl":"http://test-xxtbpy.getlearn.cn/qrcode.php?data=http%3A%2F%2Ftest-xxtbpy.getlearn.cn%2Findex%2Ftv%2Ffeedback%3Ftoken%3DMDAwMDAwMDAwMLvMdGOHe7DQtImBpIKmrM6_25tjjKh5kYGKtpWxr6GZr9ybnJKxq5eyiXh2"}
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
         * qrcodeUrl : http://test-xxtbpy.getlearn.cn/qrcode.php?data=http%3A%2F%2Ftest-xxtbpy.getlearn.cn%2Findex%2Ftv%2Ffeedback%3Ftoken%3DMDAwMDAwMDAwMLvMdGOHe7DQtImBpIKmrM6_25tjjKh5kYGKtpWxr6GZr9ybnJKxq5eyiXh2
         */
        private String qrcodeUrl;

        public void setQrcodeUrl(String qrcodeUrl) {
            this.qrcodeUrl = qrcodeUrl;
        }

        public String getQrcodeUrl() {
            return qrcodeUrl;
        }
    }
}
