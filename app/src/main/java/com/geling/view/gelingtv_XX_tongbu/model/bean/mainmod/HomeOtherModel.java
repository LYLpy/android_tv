package com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/29------更新------
 * 主页--除首页之外其他模块
 */

public class HomeOtherModel {

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

        private CourseListDatabean course_list_data;
        private List<RecommendationDatabean> recommendation_data;

        public CourseListDatabean getCourse_list_data() {
            return course_list_data;
        }

        public void setCourse_list_data(CourseListDatabean course_list_data) {
            this.course_list_data = course_list_data;
        }

        public List<RecommendationDatabean> getRecommendation_data() {
            return recommendation_data;
        }

        public void setRecommendation_data(List<RecommendationDatabean> recommendation_data) {
            this.recommendation_data = recommendation_data;
        }

        public static class CourseListDatabean {
            private List<CourseNavbean> course_nav;
            private List<CourseDatabean> course_data;

            public List<CourseNavbean> getCourse_nav() {
                return course_nav;
            }

            public void setCourse_nav(List<CourseNavbean> course_nav) {
                this.course_nav = course_nav;
            }

            public List<CourseDatabean> getCourse_data() {
                return course_data;
            }

            public void setCourse_data(List<CourseDatabean> course_data) {
                this.course_data = course_data;
            }

            public static class CourseNavbean {
                /**
                 * left_nav_id : 56
                 * left_nav_name : 学前：国学经典
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

            public static class CourseDatabean {

                private String left_nav_id;
                private int total_count;
                private int total_pages;
                private List<List<PageDatabean>> page_data;

                public String getLeft_nav_id() {
                    return left_nav_id;
                }

                public void setLeft_nav_id(String left_nav_id) {
                    this.left_nav_id = left_nav_id;
                }

                public int getTotal_count() {
                    return total_count;
                }

                public void setTotal_count(int total_count) {
                    this.total_count = total_count;
                }

                public int getTotal_pages() {
                    return total_pages;
                }

                public void setTotal_pages(int total_pages) {
                    this.total_pages = total_pages;
                }

                public List<List<PageDatabean>> getPage_data() {
                    return page_data;
                }

                public void setPage_data(List<List<PageDatabean>> page_data) {
                    this.page_data = page_data;
                }

                public static class PageDatabean {
                    /**
                     * course_id : 954
                     * course_img : http://139.9.7.208:8989/Public/Uploads/images/2019-11-28/5ddf690b69432.jpg
                     */

                    private String course_id;
                    private String course_img;

                    public String getCourse_id() {
                        return course_id;
                    }

                    public void setCourse_id(String course_id) {
                        this.course_id = course_id;
                    }

                    public String getCourse_img() {
                        return course_img;
                    }

                    public void setCourse_img(String course_img) {
                        this.course_img = course_img;
                    }
                }
            }
        }

        public static class RecommendationDatabean {
            /**
             * recommendation_img : http://139.9.7.208:8989/Public/Uploads/images/2019-11-28/5ddf6ffaa569c.jpg
             * bind_type : 1
             * bind_url : 112
             * is_loop : 0
             * course_id :
             */

            private String recommendation_img;
            private String bind_type;
            private String bind_url;
            private String is_loop;
            private String course_id;

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

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }
        }
    }
}



