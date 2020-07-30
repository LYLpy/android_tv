package com.geling.view.gelingtv_XX_tongbu.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/28------更新------
 * 主页各个模块数据
 */

public class MainNavModDataModel {

    private int code;
    private String msg;
    private List<DatabeanX> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DatabeanX> getData() {
        return data;
    }

    public void setData(List<DatabeanX> data) {
        this.data = data;
    }

    public static class DatabeanX {

        private String nav_id;
        private String nav_type;
        private String class_name;
        private Databean data;

        public String getNav_id() {
            return nav_id;
        }

        public void setNav_id(String nav_id) {
            this.nav_id = nav_id;
        }

        public String getNav_type() {
            return nav_type;
        }

        public void setNav_type(String nav_type) {
            this.nav_type = nav_type;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public Databean getData() {
            return data;
        }

        public void setData(Databean data) {
            this.data = data;
        }

        public static class Databean {
            private List<RecommendationDatabean> recommendation_data;
            private List<List<CourseListDatabean>> course_list_data;

            public List<RecommendationDatabean> getRecommendation_data() {
                return recommendation_data;
            }

            public void setRecommendation_data(List<RecommendationDatabean> recommendation_data) {
                this.recommendation_data = recommendation_data;
            }

            public List<List<CourseListDatabean>> getCourse_list_data() {
                return course_list_data;
            }

            public void setCourse_list_data(List<List<CourseListDatabean>> course_list_data) {
                this.course_list_data = course_list_data;
            }

            public static class RecommendationDatabean {
                /**
                 * recommendation_img : http://iptv3.com/Public/Uploads/images/2019-10-22/5dae71dc4efac.jpg
                 * bind_type : 1
                 * bind_url : 12
                 * is_loop : 0
                 * loop_data : [{"recommendation_img":"http://iptv3.com/Public/Uploads/images/2019-10-17/5da7bbce77b3c.png","bind_type":"1","bind_url":"sdfds"},{"recommendation_img":"http://iptv3.com/Public/Uploads/images/2019-10-17/5da7bbce78ec4.png","bind_type":"3","bind_url":"dsfdsfds"},{"recommendation_img":"http://iptv3.com/Public/Uploads/images/2019-10-17/5da7bfa608bd8.png","bind_type":"2","bind_url":"222"}]
                 */

                private String recommendation_img;
                private String bind_type;
                private String bind_url;
                private String is_loop;
                private List<LoopDatabean> loop_data;

                public String getRecommendation_img() {
                    return recommendation_img;
                }

                public void setRecommendation_img(String recommendation_img) {
                    this.recommendation_img = recommendation_img;
                }

                public String getBind_type() {
                    return bind_type;
                }

                public void setBind_type(String bind_type) {
                    this.bind_type = bind_type;
                }

                public String getBind_url() {
                    return bind_url;
                }

                public void setBind_url(String bind_url) {
                    this.bind_url = bind_url;
                }

                public String getIs_loop() {
                    return is_loop;
                }

                public void setIs_loop(String is_loop) {
                    this.is_loop = is_loop;
                }

                public List<LoopDatabean> getLoop_data() {
                    return loop_data;
                }

                public void setLoop_data(List<LoopDatabean> loop_data) {
                    this.loop_data = loop_data;
                }

                public static class LoopDatabean {
                    /**
                     * recommendation_img : http://iptv3.com/Public/Uploads/images/2019-10-17/5da7bbce77b3c.png
                     * bind_type : 1
                     * bind_url : sdfds
                     */

                    private String recommendation_img;
                    private String bind_type;
                    private String bind_url;

                    public String getRecommendation_img() {
                        return recommendation_img;
                    }

                    public void setRecommendation_img(String recommendation_img) {
                        this.recommendation_img = recommendation_img;
                    }

                    public String getBind_type() {
                        return bind_type;
                    }

                    public void setBind_type(String bind_type) {
                        this.bind_type = bind_type;
                    }

                    public String getBind_url() {
                        return bind_url;
                    }

                    public void setBind_url(String bind_url) {
                        this.bind_url = bind_url;
                    }
                }
            }

            public static class CourseListDatabean {
                /**
                 * left_nav_id : 24
                 * left_nav_name : 初中状元课堂七年级
                 */

                private String left_nav_id;
                private String left_nav_name;

                public String getLeft_nav_id() {
                    return left_nav_id;
                }

                public void setLeft_nav_id(String left_nav_id) {
                    this.left_nav_id = left_nav_id;
                }

                public String getLeft_nav_name() {
                    return left_nav_name;
                }

                public void setLeft_nav_name(String left_nav_name) {
                    this.left_nav_name = left_nav_name;
                }
            }
        }
    }
}
