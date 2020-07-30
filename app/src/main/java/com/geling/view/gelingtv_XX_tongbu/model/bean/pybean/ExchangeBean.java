package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

import java.util.List;

public class ExchangeBean {
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appExchangeList":{"total":10,"per_page":"4","current_page":1,"last_page":3,"data":[{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:59:02","type":0,"code":"5y7BC3CAEC42","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:57:28","type":0,"code":"5y8959DCD6D9","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:56:38","type":0,"code":"5yA44CBABB6B","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200221/30a03101519328580b17c83a29e5f7cb.png","use_time":"2020-01-16 17:53:29","type":0,"code":"507EB74D95B5","viptime":0,"gift_id":76,"name":"50元优惠券","validity":"2020-01-17 11:00:00"}]}}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBeanX data;

    public static ExchangeBean gsonBean(String json) {
        return new Gson().fromJson(json, ExchangeBean.class);
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
         * appExchangeList : {"total":10,"per_page":"4","current_page":1,"last_page":3,"data":[{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:59:02","type":0,"code":"5y7BC3CAEC42","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:57:28","type":0,"code":"5y8959DCD6D9","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:56:38","type":0,"code":"5yA44CBABB6B","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200221/30a03101519328580b17c83a29e5f7cb.png","use_time":"2020-01-16 17:53:29","type":0,"code":"507EB74D95B5","viptime":0,"gift_id":76,"name":"50元优惠券","validity":"2020-01-17 11:00:00"}]}
         */

        private AppExchangeListBean appExchangeList;

        public AppExchangeListBean getAppExchangeList() {
            return appExchangeList;
        }

        public void setAppExchangeList(AppExchangeListBean appExchangeList) {
            this.appExchangeList = appExchangeList;
        }

        public static class AppExchangeListBean {
            /**
             * total : 10
             * per_page : 4
             * current_page : 1
             * last_page : 3
             * data : [{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:59:02","type":0,"code":"5y7BC3CAEC42","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:57:28","type":0,"code":"5y8959DCD6D9","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png","use_time":"2020-01-16 17:56:38","type":0,"code":"5yA44CBABB6B","viptime":0,"gift_id":62,"name":"5元优惠券","validity":"2020-01-17 09:00:00"},{"icon":"http://test-xxtbpy.getlearn.cn/uploads/20200221/30a03101519328580b17c83a29e5f7cb.png","use_time":"2020-01-16 17:53:29","type":0,"code":"507EB74D95B5","viptime":0,"gift_id":76,"name":"50元优惠券","validity":"2020-01-17 11:00:00"}]
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
                 * icon : http://test-xxtbpy.getlearn.cn/uploads/20190923/9eb3cc950a8d3154326a2e614c66ad8b.png
                 * use_time : 2020-01-16 17:59:02
                 * type : 0
                 * code : 5y7BC3CAEC42
                 * viptime : 0
                 * gift_id : 62
                 * name : 5元优惠券
                 * validity : 2020-01-17 09:00:00
                 */

                private String icon;
                private String use_time;
                private int type;
                private String code;
                private int viptime;
                private int gift_id;
                private String name;
                private String validity;

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getUse_time() {
                    return use_time;
                }

                public void setUse_time(String use_time) {
                    this.use_time = use_time;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getViptime() {
                    return viptime;
                }

                public void setViptime(int viptime) {
                    this.viptime = viptime;
                }

                public int getGift_id() {
                    return gift_id;
                }

                public void setGift_id(int gift_id) {
                    this.gift_id = gift_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValidity() {
                    return validity;
                }

                public void setValidity(String validity) {
                    this.validity = validity;
                }
            }
        }
    }
}
