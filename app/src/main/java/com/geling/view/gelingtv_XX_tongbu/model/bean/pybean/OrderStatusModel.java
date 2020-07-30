package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/3/1------更新------
 * 订单状态
 */

public class OrderStatusModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"id":646,"uid":3461,"goods_id":164,"order_amount":6400,"length_time":120,"pay_amount":6400,"order_no":"GL202003011357180147733308","buyer_pay_amount":"0.00","gmt_payment":null,"preferential_amount":500,"coupon_id":91,"u_gift_id":180,"buyer_id":null,"invoice_amount":"0.00","notify_id":null,"trade_no":null,"app_auth_id":null,"buyer_logon_id":null,"order_status":0,"create_time":"2020-03-01 13:57:18","pay_time":0,"pay_type":0,"client_type":1}
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
         * id : 646
         * uid : 3461
         * goods_id : 164
         * order_amount : 6400
         * length_time : 120
         * pay_amount : 6400
         * order_no : GL202003011357180147733308
         * buyer_pay_amount : 0.00
         * gmt_payment : null
         * preferential_amount : 500
         * coupon_id : 91
         * u_gift_id : 180
         * buyer_id : null
         * invoice_amount : 0.00
         * notify_id : null
         * trade_no : null
         * app_auth_id : null
         * buyer_logon_id : null
         * order_status : 0
         * create_time : 2020-03-01 13:57:18
         * pay_time : 0
         * pay_type : 0
         * client_type : 1
         */

        private int id;
        private int uid;
        private int goods_id;
        private int order_amount;
        private int length_time;
        private int pay_amount;
        private String order_no;
        private String buyer_pay_amount;
        private Object gmt_payment;
        private int preferential_amount;
        private int coupon_id;
        private int u_gift_id;
        private Object buyer_id;
        private String invoice_amount;
        private Object notify_id;
        private Object trade_no;
        private Object app_auth_id;
        private Object buyer_logon_id;
        private int order_status;//订单状态0待付款，1已付款，2订单失效
        private String create_time;
        private int pay_time;
        private int pay_type;
        private int client_type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(int order_amount) {
            this.order_amount = order_amount;
        }

        public int getLength_time() {
            return length_time;
        }

        public void setLength_time(int length_time) {
            this.length_time = length_time;
        }

        public int getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(int pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getBuyer_pay_amount() {
            return buyer_pay_amount;
        }

        public void setBuyer_pay_amount(String buyer_pay_amount) {
            this.buyer_pay_amount = buyer_pay_amount;
        }

        public Object getGmt_payment() {
            return gmt_payment;
        }

        public void setGmt_payment(Object gmt_payment) {
            this.gmt_payment = gmt_payment;
        }

        public int getPreferential_amount() {
            return preferential_amount;
        }

        public void setPreferential_amount(int preferential_amount) {
            this.preferential_amount = preferential_amount;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public int getU_gift_id() {
            return u_gift_id;
        }

        public void setU_gift_id(int u_gift_id) {
            this.u_gift_id = u_gift_id;
        }

        public Object getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(Object buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getInvoice_amount() {
            return invoice_amount;
        }

        public void setInvoice_amount(String invoice_amount) {
            this.invoice_amount = invoice_amount;
        }

        public Object getNotify_id() {
            return notify_id;
        }

        public void setNotify_id(Object notify_id) {
            this.notify_id = notify_id;
        }

        public Object getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(Object trade_no) {
            this.trade_no = trade_no;
        }

        public Object getApp_auth_id() {
            return app_auth_id;
        }

        public void setApp_auth_id(Object app_auth_id) {
            this.app_auth_id = app_auth_id;
        }

        public Object getBuyer_logon_id() {
            return buyer_logon_id;
        }

        public void setBuyer_logon_id(Object buyer_logon_id) {
            this.buyer_logon_id = buyer_logon_id;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getPay_time() {
            return pay_time;
        }

        public void setPay_time(int pay_time) {
            this.pay_time = pay_time;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getClient_type() {
            return client_type;
        }

        public void setClient_type(int client_type) {
            this.client_type = client_type;
        }
    }
}
