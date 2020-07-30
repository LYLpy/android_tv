package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/23------更新------
 * 产品套餐bean
 */

public class MealModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":4,"per_page":"10000","current_page":1,"last_page":1,"data":[{"id":164,"set_meal_name":"季卡","price":"69.00","vm_price":"99.00","length_time":120,"icon":"20200110/c9b85f14ce9b551660f0de37494ac2f7.png","discount_price":"30.00","begin_time":1578626387,"end_time":1578758400,"discount":0,"duration":"4个月","day_price":0.58},{"id":171,"set_meal_name":"2天vip","price":"39.01","vm_price":"55.00","length_time":2,"icon":"20200115/9d27debb927178ec134406a7883d9712.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"duration":"2天","day_price":19.51},{"id":176,"set_meal_name":"7日卡","price":"0.01","vm_price":"20.00","length_time":7,"icon":"20200116/8796efc3d3dcb83ebcf85274cb67530c.png","discount_price":"9.01","begin_time":1579104000,"end_time":1579276799,"discount":0,"duration":"7天","day_price":0},{"id":177,"set_meal_name":"年卡","price":"99.00","vm_price":"169.00","length_time":365,"icon":"20200116/7f3fc7f1ac8cee683d40143a09d94b58.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"duration":"12个月","day_price":0.27}]}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DatabeanX data;

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

    public DatabeanX getData() {
        return data;
    }

    public void setData(DatabeanX data) {
        this.data = data;
    }

    public static class DatabeanX {
        /**
         * total : 4
         * per_page : 10000
         * current_page : 1
         * last_page : 1
         * data : [{"id":164,"set_meal_name":"季卡","price":"69.00","vm_price":"99.00","length_time":120,"icon":"20200110/c9b85f14ce9b551660f0de37494ac2f7.png","discount_price":"30.00","begin_time":1578626387,"end_time":1578758400,"discount":0,"duration":"4个月","day_price":0.58},{"id":171,"set_meal_name":"2天vip","price":"39.01","vm_price":"55.00","length_time":2,"icon":"20200115/9d27debb927178ec134406a7883d9712.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"duration":"2天","day_price":19.51},{"id":176,"set_meal_name":"7日卡","price":"0.01","vm_price":"20.00","length_time":7,"icon":"20200116/8796efc3d3dcb83ebcf85274cb67530c.png","discount_price":"9.01","begin_time":1579104000,"end_time":1579276799,"discount":0,"duration":"7天","day_price":0},{"id":177,"set_meal_name":"年卡","price":"99.00","vm_price":"169.00","length_time":365,"icon":"20200116/7f3fc7f1ac8cee683d40143a09d94b58.png","discount_price":"0.00","begin_time":0,"end_time":0,"discount":0,"duration":"12个月","day_price":0.27}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<Databean> data;

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

        public List<Databean> getData() {
            return data;
        }

        public void setData(List<Databean> data) {
            this.data = data;
        }

        public static class Databean {
            /**
             * id : 164
             * set_meal_name : 季卡
             * price : 69.00
             * vm_price : 99.00
             * length_time : 120
             * icon : 20200110/c9b85f14ce9b551660f0de37494ac2f7.png
             * discount_price : 30.00
             * begin_time : 1578626387
             * end_time : 1578758400
             * discount : 0
             * duration : 4个月
             * day_price : 0.58
             */

            private int id;
            private String set_meal_name;
            private String price;
            private String vm_price;
            private int length_time;
            private String icon;
            private String discount_price;//限时折扣价，discount为0时，非限时折扣价，为1时是限时折扣价
            private int begin_time;
            private int end_time;
            private int discount;//为0时，非限时折扣价，为1时是限时折扣价
            private String duration;
            private double day_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSet_meal_name() {
                return set_meal_name;
            }

            public void setSet_meal_name(String set_meal_name) {
                this.set_meal_name = set_meal_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getVm_price() {
                return vm_price;
            }

            public void setVm_price(String vm_price) {
                this.vm_price = vm_price;
            }

            public int getLength_time() {
                return length_time;
            }

            public void setLength_time(int length_time) {
                this.length_time = length_time;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(String discount_price) {
                this.discount_price = discount_price;
            }

            public int getBegin_time() {
                return begin_time;
            }

            public void setBegin_time(int begin_time) {
                this.begin_time = begin_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public double getDay_price() {
                return day_price;
            }

            public void setDay_price(double day_price) {
                this.day_price = day_price;
            }
        }
    }
}
