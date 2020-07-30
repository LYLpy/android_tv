package com.geling.view.gelingtv_XX_tongbu.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/17------更新------
 *获取二维码的bean
 */

public class QrCodeBean {

    /**
     * ret : 0
     * qrCode : http://paypic.jxtvnet.cn:9090/qrcode/20191217/1007120191217193943526117.png
     * orderTime : 2019-12-17 11:35:51
     * desc : 操作成功
     * orderId : 1007120191217193943526117
     */

    private String ret;
    private String qrCode;
    private String orderTime;
    private String desc;
    private String orderId;
    private int pollingTime;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPollingTime() {
        return pollingTime;
    }

    public void setPollingTime(int pollingTime) {
        this.pollingTime = pollingTime;
    }
}
