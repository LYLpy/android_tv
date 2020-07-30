package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/28------更新------
 * 支付dia的展示用的bean
 */

public class PayDialogShowModel {
    public static final String ALIPAY = "alipay";
    public static final String WECHAT_PAY = "wechat_pay";
    private String payPrice;
    private String mealName;
    private List<QrCodeModel> mQrCodeModelList;
    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    public List<QrCodeModel> getQrCodeModelList() {
        return mQrCodeModelList;
    }

    public void setQrCodeModelList(List<QrCodeModel> qrCodeModelList) {
        mQrCodeModelList = qrCodeModelList;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }


    public static class QrCodeModel{
        //payWay，alipay表示支付宝，wechat_pay表示微信,使用上面两个常量
        private String payWay;
        //二维码链接
        private String qrCodeUrl;

        public String getPayWay() {
            return payWay;
        }

        public void setPayWay(String payWay) {
            this.payWay = payWay;
        }

        public String getQrCodeUrl() {
            return qrCodeUrl;
        }

        public void setQrCodeUrl(String qrCodeUrl) {
            this.qrCodeUrl = qrCodeUrl;
        }
    }
}
