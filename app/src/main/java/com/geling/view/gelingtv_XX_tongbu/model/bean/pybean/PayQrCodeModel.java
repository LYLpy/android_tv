package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/3/3------更新------
 */

public class PayQrCodeModel{

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"qrcodeUrl":"http://test-xxtbpy.getlearn.cn/qrcode.php?data=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3DAKyzV9k"}
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
         * qrcodeUrl : http://test-xxtbpy.getlearn.cn/qrcode.php?data=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3DAKyzV9k
         */

        private String qrcodeUrl;

        public String getQrcodeUrl() {
            return qrcodeUrl;
        }

        public void setQrcodeUrl(String qrcodeUrl) {
            this.qrcodeUrl = qrcodeUrl;
        }
    }
}
