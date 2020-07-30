package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/28------更新------
 * 支付订单的bean
 */

public class    QrCodeOrderModel {
    /**
     * msg : 请求成功
     * data : {"order_no":"TV20200426100609837614","pay_info":{"msg":"支付接口调用成功","success":"success","pay_url":"http://30.30.101.17:8751/qrcode.php?data=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3Dm2qGFgA"},"create_time":"2020-04-26 10:06:09","coupon":0,"pay_amount":"39.01","icon":"20190903/ee03bf354fd3b9d5dcde18658e0fe93f.png","preferential_amount":"0.00","set_meal_name":"月卡","pay_time":0,"order_status":0,"coupon_id":0,"order_amount":"39.01","pay_type":0,"order_id":19581,"buyer_pay_amount":"0.00"}
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
         * order_no : TV20200426100609837614
         * pay_info : {"msg":"支付接口调用成功","success":"success","pay_url":"http://30.30.101.17:8751/qrcode.php?data=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3Dm2qGFgA"}
         * create_time : 2020-04-26 10:06:09
         * coupon : 0
         * pay_amount : 39.01
         * icon : 20190903/ee03bf354fd3b9d5dcde18658e0fe93f.png
         * preferential_amount : 0.00
         * set_meal_name : 月卡
         * pay_time : 0
         * order_status : 0
         * coupon_id : 0
         * order_amount : 39.01
         * pay_type : 0
         * order_id : 19581
         * buyer_pay_amount : 0.00
         */
        private String order_no;
        private Pay_infoEntity pay_info;
        private String create_time;
        private int coupon;
        private String pay_amount;
        private String icon;
        private String preferential_amount;
        private String set_meal_name;
        private int pay_time;
        private int order_status;
        private int coupon_id;
        private String order_amount;
        private int pay_type;
        private int order_id;
        private String buyer_pay_amount;

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public void setPay_info(Pay_infoEntity pay_info) {
            this.pay_info = pay_info;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setPreferential_amount(String preferential_amount) {
            this.preferential_amount = preferential_amount;
        }

        public void setSet_meal_name(String set_meal_name) {
            this.set_meal_name = set_meal_name;
        }

        public void setPay_time(int pay_time) {
            this.pay_time = pay_time;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public void setBuyer_pay_amount(String buyer_pay_amount) {
            this.buyer_pay_amount = buyer_pay_amount;
        }

        public String getOrder_no() {
            return order_no;
        }

        public Pay_infoEntity getPay_info() {
            return pay_info;
        }

        public String getCreate_time() {
            return create_time;
        }

        public int getCoupon() {
            return coupon;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public String getIcon() {
            return icon;
        }

        public String getPreferential_amount() {
            return preferential_amount;
        }

        public String getSet_meal_name() {
            return set_meal_name;
        }

        public int getPay_time() {
            return pay_time;
        }

        public int getOrder_status() {
            return order_status;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public int getPay_type() {
            return pay_type;
        }

        public int getOrder_id() {
            return order_id;
        }

        public String getBuyer_pay_amount() {
            return buyer_pay_amount;
        }

        public class Pay_infoEntity {
            /**
             * msg : 支付接口调用成功
             * success : success
             * pay_url : http://30.30.101.17:8751/qrcode.php?data=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3Dm2qGFgA
             */
            private String msg;
            private String success;
            private String pay_url;

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public void setSuccess(String success) {
                this.success = success;
            }

            public void setPay_url(String pay_url) {
                this.pay_url = pay_url;
            }

            public String getMsg() {
                return msg;
            }

            public String getSuccess() {
                return success;
            }

            public String getPay_url() {
                return pay_url;
            }
        }
    }


//    /**
//     * error : 10000
//     * success : success
//     * status : 200
//     * msg : 请求成功
//     * data : {"order_id":679,"order_no":"GL202003031357396256071254","order_amount":"64.00","pay_amount":"64.00","pay_type":0,"create_time":"2020-03-03 13:57:39","pay_time":0,"order_status":0,"preferential_amount":"5.00","buyer_pay_amount":"0.00","coupon_id":91,"set_meal_name":"季卡","icon":"20200110/c9b85f14ce9b551660f0de37494ac2f7.png","coupon":1,"pay_info":{"success":"success","msg":"支付接口调用成功","pay_url":"MDAwMDAwMDAwMMWUiaKefs7ZtJ5rr5mVztm_uZ-pi9Gcl5Z9p5fIjX-ksZV1q4mOrNW7jYF-itC3nw"}}
//     */
//
//    private int error;
//    private String success;
//    private String status;
//    private String msg;
//    private Databean data;
//
//    public int getError() {
//        return error;
//    }
//
//    public void setError(int error) {
//        this.error = error;
//    }
//
//    public String getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(String success) {
//        this.success = success;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public Databean getData() {
//        return data;
//    }
//
//    public void setData(Databean data) {
//        this.data = data;
//    }
//
//    public static class Databean {
//        /**
//         * order_id : 679
//         * order_no : GL202003031357396256071254
//         * order_amount : 64.00
//         * pay_amount : 64.00
//         * pay_type : 0
//         * create_time : 2020-03-03 13:57:39
//         * pay_time : 0
//         * order_status : 0
//         * preferential_amount : 5.00
//         * buyer_pay_amount : 0.00
//         * coupon_id : 91
//         * set_meal_name : 季卡
//         * icon : 20200110/c9b85f14ce9b551660f0de37494ac2f7.png
//         * coupon : 1
//         * pay_info : {"success":"success","msg":"支付接口调用成功","pay_url":"MDAwMDAwMDAwMMWUiaKefs7ZtJ5rr5mVztm_uZ-pi9Gcl5Z9p5fIjX-ksZV1q4mOrNW7jYF-itC3nw"}
//         */
//
//        private int order_id;
//        private String order_no;
//        private String order_amount;
//        private String pay_amount;
//        private int pay_type;
//        private String create_time;
//        private int pay_time;
//        private int order_status;
//        private String preferential_amount;
//        private String buyer_pay_amount;
//        private int coupon_id;
//        private String set_meal_name;
//        private String icon;
//        private int coupon;
//        private PayInfobean pay_info;
//
//        public int getOrder_id() {
//            return order_id;
//        }
//
//        public void setOrder_id(int order_id) {
//            this.order_id = order_id;
//        }
//
//        public String getOrder_no() {
//            return order_no;
//        }
//
//        public void setOrder_no(String order_no) {
//            this.order_no = order_no;
//        }
//
//        public String getOrder_amount() {
//            return order_amount;
//        }
//
//        public void setOrder_amount(String order_amount) {
//            this.order_amount = order_amount;
//        }
//
//        public String getPay_amount() {
//            return pay_amount;
//        }
//
//        public void setPay_amount(String pay_amount) {
//            this.pay_amount = pay_amount;
//        }
//
//        public int getPay_type() {
//            return pay_type;
//        }
//
//        public void setPay_type(int pay_type) {
//            this.pay_type = pay_type;
//        }
//
//        public String getCreate_time() {
//            return create_time;
//        }
//
//        public void setCreate_time(String create_time) {
//            this.create_time = create_time;
//        }
//
//        public int getPay_time() {
//            return pay_time;
//        }
//
//        public void setPay_time(int pay_time) {
//            this.pay_time = pay_time;
//        }
//
//        public int getOrder_status() {
//            return order_status;
//        }
//
//        public void setOrder_status(int order_status) {
//            this.order_status = order_status;
//        }
//
//        public String getPreferential_amount() {
//            return preferential_amount;
//        }
//
//        public void setPreferential_amount(String preferential_amount) {
//            this.preferential_amount = preferential_amount;
//        }
//
//        public String getBuyer_pay_amount() {
//            return buyer_pay_amount;
//        }
//
//        public void setBuyer_pay_amount(String buyer_pay_amount) {
//            this.buyer_pay_amount = buyer_pay_amount;
//        }
//
//        public int getCoupon_id() {
//            return coupon_id;
//        }
//
//        public void setCoupon_id(int coupon_id) {
//            this.coupon_id = coupon_id;
//        }
//
//        public String getSet_meal_name() {
//            return set_meal_name;
//        }
//
//        public void setSet_meal_name(String set_meal_name) {
//            this.set_meal_name = set_meal_name;
//        }
//
//        public String getIcon() {
//            return icon;
//        }
//
//        public void setIcon(String icon) {
//            this.icon = icon;
//        }
//
//        public int getCoupon() {
//            return coupon;
//        }
//
//        public void setCoupon(int coupon) {
//            this.coupon = coupon;
//        }
//
//        public PayInfobean getPay_info() {
//            return pay_info;
//        }
//
//        public void setPay_info(PayInfobean pay_info) {
//            this.pay_info = pay_info;
//        }
//
//        public static class PayInfobean {
//            /**
//             * success : success
//             * msg : 支付接口调用成功
//             * pay_url : MDAwMDAwMDAwMMWUiaKefs7ZtJ5rr5mVztm_uZ-pi9Gcl5Z9p5fIjX-ksZV1q4mOrNW7jYF-itC3nw
//             */
//
//            private String success;
//            private String msg;
//            private String pay_url;
//
//            public String getSuccess() {
//                return success;
//            }
//
//            public void setSuccess(String success) {
//                this.success = success;
//            }
//
//            public String getMsg() {
//                return msg;
//            }
//
//            public void setMsg(String msg) {
//                this.msg = msg;
//            }
//
//            public String getPay_url() {
//                return pay_url;
//            }
//
//            public void setPay_url(String pay_url) {
//                this.pay_url = pay_url;
//            }
//        }
//    }
}
