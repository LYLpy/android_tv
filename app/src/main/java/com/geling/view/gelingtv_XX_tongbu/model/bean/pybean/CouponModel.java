package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/19------更新------
 * 优惠券的bean
 */

public class CouponModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : [{"id":1,"coupon":"10元优惠券","coupon_price":10,"icon":"http://www.baidu.com/xxxx.jpg","status":1},{"id":2,"coupon":"20元优惠券","coupon_price":20,"icon":"http://www.baidu.com/xxxx.jpg","status":2},{"id":3,"coupon":"30元优惠券","coupon_price":30,"icon":"http://www.baidu.com/xxxx.jpg","status":0}]
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private List<Databean> data;

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

    public List<Databean> getData() {
        return data;
    }

    public void setData(List<Databean> data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * id : 1
         * coupon : 10元优惠券
         * coupon_price : 10
         * icon : http://www.baidu.com/xxxx.jpg
         * status : 1
         */

        private int id;
        private String coupon;
        private int coupon_price;
        private String icon;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public int getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(int coupon_price) {
            this.coupon_price = coupon_price;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
