package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/24------更新------
 * 根据价格获取的优惠券bean
 */

public class CouponByPriceModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appGiftList":{"total":1,"per_page":"10000","current_page":1,"last_page":1,"data":[{"giftName":"5元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200220/16c77c2bb4499e400315ca0f97a689d3.png","couponCurrency":5,"couponCondition":10,"id":91,"startCouponDateTime":"2020.02.20 00:00","u_gift_id":154,"endCouponDateTime":"2020.02.29 00:00","couponStatus":1,"status":1}]}}
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
         * appGiftList : {"total":1,"per_page":"10000","current_page":1,"last_page":1,"data":[{"giftName":"5元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200220/16c77c2bb4499e400315ca0f97a689d3.png","couponCurrency":5,"couponCondition":10,"id":91,"startCouponDateTime":"2020.02.20 00:00","u_gift_id":154,"endCouponDateTime":"2020.02.29 00:00","couponStatus":1,"status":1}]}
         */

        private AppGiftListbean appGiftList;

        public AppGiftListbean getAppGiftList() {
            return appGiftList;
        }

        public void setAppGiftList(AppGiftListbean appGiftList) {
            this.appGiftList = appGiftList;
        }

        public static class AppGiftListbean {
            /**
             * total : 1
             * per_page : 10000
             * current_page : 1
             * last_page : 1
             * data : [{"giftName":"5元优惠券","icon":"http://test-xxtbpy.getlearn.cn/uploads/20200220/16c77c2bb4499e400315ca0f97a689d3.png","couponCurrency":5,"couponCondition":10,"id":91,"startCouponDateTime":"2020.02.20 00:00","u_gift_id":154,"endCouponDateTime":"2020.02.29 00:00","couponStatus":1,"status":1}]
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
                 * giftName : 5元优惠券
                 * icon : http://test-xxtbpy.getlearn.cn/uploads/20200220/16c77c2bb4499e400315ca0f97a689d3.png
                 * couponCurrency : 5
                 * couponCondition : 10
                 * id : 91
                 * startCouponDateTime : 2020.02.20 00:00
                 * u_gift_id : 154
                 * endCouponDateTime : 2020.02.29 00:00
                 * couponStatus : 1
                 * status : 1
                 */

                private String giftName;
                private String icon;
                private double couponCurrency;//优惠券价格
                private int couponCondition;//优惠券使用条件，满多少可以使用
                private int id;
                private String startCouponDateTime;
                private int u_gift_id;
                private String endCouponDateTime;
                private int couponStatus;
                private int status;

                public String getGiftName() {
                    return giftName;
                }

                public void setGiftName(String giftName) {
                    this.giftName = giftName;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public double getCouponCurrency() {
                    return couponCurrency;
                }

                public void setCouponCurrency(double couponCurrency) {
                    this.couponCurrency = couponCurrency;
                }

                public int getCouponCondition() {
                    return couponCondition;
                }

                public void setCouponCondition(int couponCondition) {
                    this.couponCondition = couponCondition;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStartCouponDateTime() {
                    return startCouponDateTime;
                }

                public void setStartCouponDateTime(String startCouponDateTime) {
                    this.startCouponDateTime = startCouponDateTime;
                }

                public int getU_gift_id() {
                    return u_gift_id;
                }

                public void setU_gift_id(int u_gift_id) {
                    this.u_gift_id = u_gift_id;
                }

                public String getEndCouponDateTime() {
                    return endCouponDateTime;
                }

                public void setEndCouponDateTime(String endCouponDateTime) {
                    this.endCouponDateTime = endCouponDateTime;
                }

                public int getCouponStatus() {
                    return couponStatus;
                }

                public void setCouponStatus(int couponStatus) {
                    this.couponStatus = couponStatus;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
