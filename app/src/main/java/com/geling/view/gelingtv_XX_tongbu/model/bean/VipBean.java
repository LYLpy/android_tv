package com.geling.view.gelingtv_XX_tongbu.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/1/6------更新------
 * 是否是vip的bean
 */

public class VipBean {

    private int vipType;//1是线下VIP，2是线上vip
    private String vipDate;//vip到期时间，线上才有这个日期
    private boolean isVip;//是否是vip


    public VipBean(int vipType, String vipDate, boolean isVip) {
        this.vipType = vipType;
        this.vipDate = vipDate;
        this.isVip = isVip;
    }

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public String getVipDate() {
        return vipDate;
    }

    public void setVipDate(String vipDate) {
        this.vipDate = vipDate;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
