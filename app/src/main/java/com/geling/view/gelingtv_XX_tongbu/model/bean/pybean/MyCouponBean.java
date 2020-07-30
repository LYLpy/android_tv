package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

import java.util.List;

public class MyCouponBean {
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appGiftList":{"total":4,"per_page":10,"current_page":1,"last_page":1,"data":[{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60}]}}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBeanX data;

    public static MyCouponBean gsonBean(String json) {
        return new Gson().fromJson(json, MyCouponBean.class);
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
         * appGiftList : {"total":4,"per_page":10,"current_page":1,"last_page":1,"data":[{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60}]}
         */

        private AppGiftListBean appGiftList;

        public AppGiftListBean getAppGiftList() {
            return appGiftList;
        }

        public void setAppGiftList(AppGiftListBean appGiftList) {
            this.appGiftList = appGiftList;
        }

        public static class AppGiftListBean {
            /**
             * total : 4
             * per_page : 10
             * current_page : 1
             * last_page : 1
             * data : [{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60},{"giftName":"优惠券","icon":"20190722\\1ad33b9ae0a11134f6593ca8d6a0c083.jpg","startCouponDateTime":"2019-07-22 00:00:00","endCouponDateTime":"2019-07-25 00:00:00","couponStatus":-1,"couponCondition":60}]
             */

            private int total;
            private int per_page;
            private int current_page;
            private int last_page;
            private List<DataBean> data;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
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
                 * giftName : 优惠券
                 * icon : 20190722\1ad33b9ae0a11134f6593ca8d6a0c083.jpg
                 * startCouponDateTime : 2019-07-22 00:00:00
                 * endCouponDateTime : 2019-07-25 00:00:00
                 * couponStatus : -1
                 * couponCondition : 60
                 */

                private String giftName;
                private String icon;
                private String startCouponDateTime;
                private String endCouponDateTime;
                private int couponStatus;
                private int couponCondition;

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

                public String getStartCouponDateTime() {
                    return startCouponDateTime;
                }

                public void setStartCouponDateTime(String startCouponDateTime) {
                    this.startCouponDateTime = startCouponDateTime;
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

                public int getCouponCondition() {
                    return couponCondition;
                }

                public void setCouponCondition(int couponCondition) {
                    this.couponCondition = couponCondition;
                }
            }
        }
    }
}
