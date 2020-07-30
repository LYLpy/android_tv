package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

import java.util.List;

public class MyOrderBean {
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":7,"per_page":"20","current_page":1,"last_page":1,"data":[{"order_id":452,"order_no":"GL202001171036411126010597","order_amount":"0.01","pay_amount":"0.01","pay_type":1,"create_time":"2020-01-17 10:36","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"39.00","buyer_pay_amount":"0.01","coupon_id":87,"set_meal_name":"2天vip","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/9d27debb927178ec134406a7883d9712.png","coupon":1},{"order_id":451,"order_no":"GL202001171036233950871880","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:36","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":90,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1},{"order_id":450,"order_no":"GL202001171034398270118541","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:34","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":90,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1},{"order_id":449,"order_no":"GL202001171032267571062082","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:32","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"39.00","buyer_pay_amount":"0.01","coupon_id":87,"set_meal_name":"2天vip","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/9d27debb927178ec134406a7883d9712.png","coupon":1},{"order_id":448,"order_no":"GL202001171030391350496738","order_amount":"0.01","pay_amount":"0.01","pay_type":1,"create_time":"2020-01-17 10:30","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":90,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1},{"order_id":447,"order_no":"GL202001171030051052760350","order_amount":"0.01","pay_amount":"0.01","pay_type":1,"create_time":"2020-01-17 10:30","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"39.00","buyer_pay_amount":"0.01","coupon_id":86,"set_meal_name":"2天vip","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/9d27debb927178ec134406a7883d9712.png","coupon":1},{"order_id":446,"order_no":"GL202001171029160916754862","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:29","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":75,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1}]}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBeanX data;

    public static MyOrderBean gsonBean(String json) {
        return new Gson().fromJson(json, MyOrderBean.class);
    }

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 7
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"order_id":452,"order_no":"GL202001171036411126010597","order_amount":"0.01","pay_amount":"0.01","pay_type":1,"create_time":"2020-01-17 10:36","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"39.00","buyer_pay_amount":"0.01","coupon_id":87,"set_meal_name":"2天vip","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/9d27debb927178ec134406a7883d9712.png","coupon":1},{"order_id":451,"order_no":"GL202001171036233950871880","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:36","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":90,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1},{"order_id":450,"order_no":"GL202001171034398270118541","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:34","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":90,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1},{"order_id":449,"order_no":"GL202001171032267571062082","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:32","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"39.00","buyer_pay_amount":"0.01","coupon_id":87,"set_meal_name":"2天vip","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/9d27debb927178ec134406a7883d9712.png","coupon":1},{"order_id":448,"order_no":"GL202001171030391350496738","order_amount":"0.01","pay_amount":"0.01","pay_type":1,"create_time":"2020-01-17 10:30","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":90,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1},{"order_id":447,"order_no":"GL202001171030051052760350","order_amount":"0.01","pay_amount":"0.01","pay_type":1,"create_time":"2020-01-17 10:30","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"39.00","buyer_pay_amount":"0.01","coupon_id":86,"set_meal_name":"2天vip","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200115/9d27debb927178ec134406a7883d9712.png","coupon":1},{"order_id":446,"order_no":"GL202001171029160916754862","order_amount":"0.01","pay_amount":"0.01","pay_type":0,"create_time":"2020-01-17 10:29","pay_time":"1970-01-01 08:00","order_status":1,"preferential_amount":"9.00","buyer_pay_amount":"0.01","coupon_id":75,"set_meal_name":"7日卡","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200116/8796efc3d3dcb83ebcf85274cb67530c.png","coupon":1}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * order_id : 452
             * order_no : GL202001171036411126010597
             * order_amount : 0.01
             * pay_amount : 0.01
             * pay_type : 1
             * create_time : 2020-01-17 10:36
             * pay_time : 1970-01-01 08:00
             * order_status : 1
             * preferential_amount : 39.00
             * buyer_pay_amount : 0.01
             * coupon_id : 87
             * set_meal_name : 2天vip
             * icon : http://test-xxtbpy.getlearn.cn/uploads/20200115/9d27debb927178ec134406a7883d9712.png
             * coupon : 1
             */

            private int order_id;
            private String order_no;
            private String order_amount;
            private String pay_amount;
            private int pay_type;
            private String create_time;
            private String pay_time;
            private int order_status;
            private String preferential_amount;
            private String buyer_pay_amount;
            private int coupon_id;
            private String set_meal_name;
            private String icon;
            private int coupon;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;
            }

            public int getPay_type() {
                return pay_type;
            }

            public void setPay_type(int pay_type) {
                this.pay_type = pay_type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }

            public String getPreferential_amount() {
                return preferential_amount;
            }

            public void setPreferential_amount(String preferential_amount) {
                this.preferential_amount = preferential_amount;
            }

            public String getBuyer_pay_amount() {
                return buyer_pay_amount;
            }

            public void setBuyer_pay_amount(String buyer_pay_amount) {
                this.buyer_pay_amount = buyer_pay_amount;
            }

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getSet_meal_name() {
                return set_meal_name;
            }

            public void setSet_meal_name(String set_meal_name) {
                this.set_meal_name = set_meal_name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getCoupon() {
                return coupon;
            }

            public void setCoupon(int coupon) {
                this.coupon = coupon;
            }
        }
    }
}
