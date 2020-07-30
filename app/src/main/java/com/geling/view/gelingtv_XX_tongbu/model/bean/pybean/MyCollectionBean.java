package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import com.google.gson.Gson;

import java.util.List;

public class MyCollectionBean {
    public static MyCollectionBean gsonBean(String json) {
        return new Gson().fromJson(json, MyCollectionBean.class);
    }
    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"appUserCollectionList":{"total":11,"per_page":"10","current_page":1,"last_page":2,"data":[{"id":8659,"name":"人教部编版小学语文四年级上册","icon":"","countClassHour":33,"countUser":630},{"id":8659,"name":"人教部编版小学语文四年级上册","icon":"","countClassHour":33,"countUser":630},{"id":192,"name":"北师大版小学语文四年级上册","icon":"","countClassHour":36,"countUser":15},{"id":8448,"name":"沪教版小学语文四年级上册","icon":"","countClassHour":40,"countUser":42},{"id":1320,"name":"北师大版小学语文一年级上册","icon":"","countClassHour":40,"countUser":101},{"id":2006,"name":"状元大课堂数学一年级上册","icon":"","countClassHour":27,"countUser":61},{"id":1047,"name":"妙解教材人教版PEP小学英语四年级上册","icon":"","countClassHour":12,"countUser":114},{"id":1314,"name":"苏教版小学数学二年级上册","icon":"","countClassHour":12,"countUser":137},{"id":1316,"name":"苏教版小学数学三年级上册","icon":"","countClassHour":8,"countUser":158},{"id":8429,"name":"状元大课堂数学二年级下册","icon":"","countClassHour":32,"countUser":146}]}}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DataBeanX data;

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
         * appUserCollectionList : {"total":11,"per_page":"10","current_page":1,"last_page":2,"data":[{"id":8659,"name":"人教部编版小学语文四年级上册","icon":"","countClassHour":33,"countUser":630},{"id":8659,"name":"人教部编版小学语文四年级上册","icon":"","countClassHour":33,"countUser":630},{"id":192,"name":"北师大版小学语文四年级上册","icon":"","countClassHour":36,"countUser":15},{"id":8448,"name":"沪教版小学语文四年级上册","icon":"","countClassHour":40,"countUser":42},{"id":1320,"name":"北师大版小学语文一年级上册","icon":"","countClassHour":40,"countUser":101},{"id":2006,"name":"状元大课堂数学一年级上册","icon":"","countClassHour":27,"countUser":61},{"id":1047,"name":"妙解教材人教版PEP小学英语四年级上册","icon":"","countClassHour":12,"countUser":114},{"id":1314,"name":"苏教版小学数学二年级上册","icon":"","countClassHour":12,"countUser":137},{"id":1316,"name":"苏教版小学数学三年级上册","icon":"","countClassHour":8,"countUser":158},{"id":8429,"name":"状元大课堂数学二年级下册","icon":"","countClassHour":32,"countUser":146}]}
         */

        private AppUserCollectionListBean appUserCollectionList;

        public AppUserCollectionListBean getAppUserCollectionList() {
            return appUserCollectionList;
        }

        public void setAppUserCollectionList(AppUserCollectionListBean appUserCollectionList) {
            this.appUserCollectionList = appUserCollectionList;
        }

        public static class AppUserCollectionListBean {
            /**
             * total : 11
             * per_page : 10
             * current_page : 1
             * last_page : 2
             * data : [{"id":8659,"name":"人教部编版小学语文四年级上册","icon":"","countClassHour":33,"countUser":630},{"id":8659,"name":"人教部编版小学语文四年级上册","icon":"","countClassHour":33,"countUser":630},{"id":192,"name":"北师大版小学语文四年级上册","icon":"","countClassHour":36,"countUser":15},{"id":8448,"name":"沪教版小学语文四年级上册","icon":"","countClassHour":40,"countUser":42},{"id":1320,"name":"北师大版小学语文一年级上册","icon":"","countClassHour":40,"countUser":101},{"id":2006,"name":"状元大课堂数学一年级上册","icon":"","countClassHour":27,"countUser":61},{"id":1047,"name":"妙解教材人教版PEP小学英语四年级上册","icon":"","countClassHour":12,"countUser":114},{"id":1314,"name":"苏教版小学数学二年级上册","icon":"","countClassHour":12,"countUser":137},{"id":1316,"name":"苏教版小学数学三年级上册","icon":"","countClassHour":8,"countUser":158},{"id":8429,"name":"状元大课堂数学二年级下册","icon":"","countClassHour":32,"countUser":146}]
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
                 * id : 8659
                 * name : 人教部编版小学语文四年级上册
                 * icon :
                 * countClassHour : 33
                 * countUser : 630
                 */

                private int id;
                private String name;
                private String icon;
                private int countClassHour;
                private int countUser;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public int getCountClassHour() {
                    return countClassHour;
                }

                public void setCountClassHour(int countClassHour) {
                    this.countClassHour = countClassHour;
                }

                public int getCountUser() {
                    return countUser;
                }

                public void setCountUser(int countUser) {
                    this.countUser = countUser;
                }
            }
        }
    }
}
