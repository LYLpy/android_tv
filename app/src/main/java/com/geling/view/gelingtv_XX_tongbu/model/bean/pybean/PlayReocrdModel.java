package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;


import com.google.gson.Gson;

import java.util.List;

public class PlayReocrdModel {

    public static  PlayReocrdModel gsonBean(String json){
        return new Gson().fromJson(json,PlayReocrdModel.class);
    }

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":10,"per_page":"20","current_page":1,"last_page":1,"data":[{"time":"2020年02月24日","week":"星期一","list":[{"course_id":2921,"class_name":"教科版广州小学英语三起三年级上册","click":29,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f3e3c32f072fbe707ce3bd09a05c6b96.png","name":"教科版广州小学英语三起三年级上册","update_time":"2020-02-24 10:51:55","time":"2020年02月24日","week":"星期一","rate":8}]},{"time":"2020年02月23日","week":"星期日","list":[{"course_id":6399,"class_name":"小学奥数五年级下册","click":17,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20191119/9ad18b89fe9f11c0beda7d0aa349bc78.png","name":"小学奥数五年级下册","update_time":"2020-02-23 12:14:40","time":"2020年02月23日","week":"星期日","rate":11},{"course_id":2752,"class_name":"语文A版小学语文四年级上册","click":4,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f11ba25e2d8acb9061089de90df4f2e7.png","name":"语文A版小学语文四年级上册","update_time":"2020-02-23 12:09:26","time":"2020年02月23日","week":"星期日","rate":3},{"course_id":2751,"class_name":"语文A版小学语文三年级上册","click":4,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f232ec31e0d21a4451af9348adb9ccfa.png","name":"语文A版小学语文三年级上册","update_time":"2020-02-23 12:09:20","time":"2020年02月23日","week":"星期日","rate":6},{"course_id":2750,"class_name":"语文A版小学语文二年级上册","click":2,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/15d16c9a13dc7f6137186315a3c845a4.png","name":"语文A版小学语文二年级上册","update_time":"2020-02-23 12:09:16","time":"2020年02月23日","week":"星期日","rate":3},{"course_id":6438,"class_name":"语文A版小学语文四年级下册","click":13,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/8026665ad8054eb64b66cfc4d2abb33d.png","name":"语文A版小学语文四年级下册","update_time":"2020-02-23 12:09:05","time":"2020年02月23日","week":"星期日","rate":6},{"course_id":6439,"class_name":"语文A版小学语文五年级下册","click":4,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/de1ec6e84b93b251e08714f63d6b50ae.png","name":"语文A版小学语文五年级下册","update_time":"2020-02-23 12:08:58","time":"2020年02月23日","week":"星期日","rate":4},{"course_id":6023,"class_name":"人教部编版小学语文一年级上册","click":4029,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/14854ad3a2d834da844503959119e63d.png","name":"人教部编版小学语文一年级上册","update_time":"2020-02-23 11:12:17","time":"2020年02月23日","week":"星期日","rate":395}]},{"time":"2019年11月05日","week":"星期二","list":[{"course_id":8610,"class_name":"沪教版小学英语3起六年级下册","click":18,"icon":"http://images.gelearning.net/uploads/","name":"沪教版小学英语3起六年级下册","update_time":"2019-11-05 17:36:45","time":"2019年11月05日","week":"星期二","rate":8},{"course_id":272,"class_name":"苏教版小学数学六年级上册","click":111,"icon":"http://images.gelearning.net/uploads/","name":"苏教版小学数学六年级上册","update_time":"2019-11-05 17:36:40","time":"2019年11月05日","week":"星期二","rate":60}]}]}
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
         * total : 10
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"time":"2020年02月24日","week":"星期一","list":[{"course_id":2921,"class_name":"教科版广州小学英语三起三年级上册","click":29,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f3e3c32f072fbe707ce3bd09a05c6b96.png","name":"教科版广州小学英语三起三年级上册","update_time":"2020-02-24 10:51:55","time":"2020年02月24日","week":"星期一","rate":8}]},{"time":"2020年02月23日","week":"星期日","list":[{"course_id":6399,"class_name":"小学奥数五年级下册","click":17,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20191119/9ad18b89fe9f11c0beda7d0aa349bc78.png","name":"小学奥数五年级下册","update_time":"2020-02-23 12:14:40","time":"2020年02月23日","week":"星期日","rate":11},{"course_id":2752,"class_name":"语文A版小学语文四年级上册","click":4,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f11ba25e2d8acb9061089de90df4f2e7.png","name":"语文A版小学语文四年级上册","update_time":"2020-02-23 12:09:26","time":"2020年02月23日","week":"星期日","rate":3},{"course_id":2751,"class_name":"语文A版小学语文三年级上册","click":4,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f232ec31e0d21a4451af9348adb9ccfa.png","name":"语文A版小学语文三年级上册","update_time":"2020-02-23 12:09:20","time":"2020年02月23日","week":"星期日","rate":6},{"course_id":2750,"class_name":"语文A版小学语文二年级上册","click":2,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/15d16c9a13dc7f6137186315a3c845a4.png","name":"语文A版小学语文二年级上册","update_time":"2020-02-23 12:09:16","time":"2020年02月23日","week":"星期日","rate":3},{"course_id":6438,"class_name":"语文A版小学语文四年级下册","click":13,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/8026665ad8054eb64b66cfc4d2abb33d.png","name":"语文A版小学语文四年级下册","update_time":"2020-02-23 12:09:05","time":"2020年02月23日","week":"星期日","rate":6},{"course_id":6439,"class_name":"语文A版小学语文五年级下册","click":4,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/de1ec6e84b93b251e08714f63d6b50ae.png","name":"语文A版小学语文五年级下册","update_time":"2020-02-23 12:08:58","time":"2020年02月23日","week":"星期日","rate":4},{"course_id":6023,"class_name":"人教部编版小学语文一年级上册","click":4029,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/14854ad3a2d834da844503959119e63d.png","name":"人教部编版小学语文一年级上册","update_time":"2020-02-23 11:12:17","time":"2020年02月23日","week":"星期日","rate":395}]},{"time":"2019年11月05日","week":"星期二","list":[{"course_id":8610,"class_name":"沪教版小学英语3起六年级下册","click":18,"icon":"http://images.gelearning.net/uploads/","name":"沪教版小学英语3起六年级下册","update_time":"2019-11-05 17:36:45","time":"2019年11月05日","week":"星期二","rate":8},{"course_id":272,"class_name":"苏教版小学数学六年级上册","click":111,"icon":"http://images.gelearning.net/uploads/","name":"苏教版小学数学六年级上册","update_time":"2019-11-05 17:36:40","time":"2019年11月05日","week":"星期二","rate":60}]}]
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
             * time : 2020年02月24日
             * week : 星期一
             * list : [{"course_id":2921,"class_name":"教科版广州小学英语三起三年级上册","click":29,"icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f3e3c32f072fbe707ce3bd09a05c6b96.png","name":"教科版广州小学英语三起三年级上册","update_time":"2020-02-24 10:51:55","time":"2020年02月24日","week":"星期一","rate":8}]
             */

            private String time;
            private String week;
            private List<ListBean> list;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * course_id : 2921
                 * class_name : 教科版广州小学英语三起三年级上册
                 * click : 29
                 * icon : http://test-xxtbpy.getlearn.cn/uploads/20190903/f3e3c32f072fbe707ce3bd09a05c6b96.png
                 * name : 教科版广州小学英语三起三年级上册
                 * update_time : 2020-02-24 10:51:55
                 * time : 2020年02月24日
                 * week : 星期一
                 * rate : 8
                 */

                private int course_id;
                private String class_name;
                private int click;
                private String icon;
                private String name;
                private String update_time;
                private String time;
                private String week;
                private int rate;

                public int getCourse_id() {
                    return course_id;
                }

                public void setCourse_id(int course_id) {
                    this.course_id = course_id;
                }

                public String getClass_name() {
                    return class_name;
                }

                public void setClass_name(String class_name) {
                    this.class_name = class_name;
                }

                public int getClick() {
                    return click;
                }

                public void setClick(int click) {
                    this.click = click;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public int getRate() {
                    return rate;
                }

                public void setRate(int rate) {
                    this.rate = rate;
                }
            }
        }
    }


//
//    public String tv_tiem;
//
//    public List<Data> data1 ;
//    public static class Data {
//        public  int imag;
//        public  String tvname;
//        public  int icon;
//        public String indate;
//        public Data(int imag,int icon,String tvname,String indate){
//            this.icon=icon;
//            this.tvname = tvname;
//            this.imag = imag;
//            this.indate= indate;
//        }
//
//        public int getImag() {
//            return imag;
//        }
//
//        public String getTvname() {
//            return tvname;
//        }
//
//        public int getIcon() {
//            return icon;
//        }
//
//        public String getIndate() {
//            return indate;
//        }
//    }
//
//    public PlayCreordModel(String tv_tiem,List<Data> list){
//        this.tv_tiem = tv_tiem;
//        this.data1 =list;
//    }
//
//
//    public String getTv_tiem() {
//        return tv_tiem;
//    }
//
//    public List<Data> getData1() {
//        return data1;
//    }
}
