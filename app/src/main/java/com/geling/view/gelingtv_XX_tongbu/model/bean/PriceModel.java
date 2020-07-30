package com.geling.view.gelingtv_XX_tongbu.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/18------更新------
 * 价格bean
 */

public class PriceModel {

    private String price;
    private String goods_id;
    private String average;//平均XX元/天
    private String goods_type;//商品类型，年卡/季卡/月卡


    public PriceModel(String price, String goodsId, String average, String goodsType) {
        this.price = price;
        this.goods_id = goodsId;
        this.average = average;
        goods_type = goodsType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }
}
