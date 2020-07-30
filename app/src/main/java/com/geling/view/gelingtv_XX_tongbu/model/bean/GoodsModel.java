package com.geling.view.gelingtv_XX_tongbu.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/18------更新------
 * 产品包的bean
 */

public class GoodsModel {


    /**
     * goodsId : 1
     * totalFeeOriginal : 4800
     * totalFee : 3500
     * goodsCode : rkb0001
     * goodsText : 平均:0.2/天
     * goodsPic : Public/image/..
     * goodsName : 任看包月计费
     */

    private int goodsId;
    private int totalFeeOriginal;
    private int totalFee;
    private String goodsCode;
    private String goodsText;
    private String goodsPic;
    private String goodsName;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getTotalFeeOriginal() {
        return totalFeeOriginal;
    }

    public void setTotalFeeOriginal(int totalFeeOriginal) {
        this.totalFeeOriginal = totalFeeOriginal;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsText() {
        return goodsText;
    }

    public void setGoodsText(String goodsText) {
        this.goodsText = goodsText;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
