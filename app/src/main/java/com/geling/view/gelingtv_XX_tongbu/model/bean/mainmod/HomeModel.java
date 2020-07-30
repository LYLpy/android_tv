package com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/28------更新------
 * 主页--首页模块数据
 */

public class HomeModel {


    /**
     * nav_id : 1
     * nav_type : 1
     * class_name : 21
     * index_module_id : 1
     * data : {"recommendation_data":[{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd2167d28b.jpg","bind_type":"1","bind_url":"112","is_loop":"0","course_id":""},{"is_loop":1,"loop_data":[{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-22/5dd7c57035ed2.jpg","bind_type":"3","bind_url":"https://www.baidu.com/"},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-22/5dd7c570365b8.jpg","bind_type":"3","bind_url":"https://www.baidu.com/"}]},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd1bc784d6.jpg","bind_type":"3","bind_url":"https://www.baidu.com/","is_loop":"0","course_id":""},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd2ca15132.jpg","bind_type":"3","bind_url":"https://www.baidu.com/","is_loop":"0","course_id":""},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd1d2dd694.jpg","bind_type":"3","bind_url":"https://www.baidu.com/","is_loop":"0","course_id":""},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd1e661558.jpg","bind_type":"3","bind_url":"https://www.baidu.com/","is_loop":"0","course_id":""},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd1faba087.jpg","bind_type":"3","bind_url":"https://www.baidu.com/","is_loop":"0","course_id":""},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd208345bb.jpg","bind_type":"3","bind_url":"https://www.baidu.com/","is_loop":"0","course_id":""}],"course_list_data":[]}
     */

    private String nav_id;
    private String nav_type;
    private String class_name;
    private String index_module_id;
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

    public String getIndex_module_id() {
        return index_module_id;
    }

    public void setIndex_module_id(String index_module_id) {
        this.index_module_id = index_module_id;
    }

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        private List<RecommendationDatabean> recommendation_data;

        public List<RecommendationDatabean> getRecommendation_data() {
            return recommendation_data;
        }

        public void setRecommendation_data(List<RecommendationDatabean> recommendation_data) {
            this.recommendation_data = recommendation_data;
        }

        public static class RecommendationDatabean {
            /**
             * recommendation_img : http://139.9.7.208:8989/Public/Uploads/images/2019-11-26/5ddcd2167d28b.jpg
             * bind_type : 1
             * bind_url : 112
             * is_loop : 0
             * course_id :
             * loop_data : [{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-22/5dd7c57035ed2.jpg","bind_type":"3","bind_url":"https://www.baidu.com/"},{"recommendation_img":"http://139.9.7.208:8989/Public/Uploads/images/2019-11-22/5dd7c570365b8.jpg","bind_type":"3","bind_url":"https://www.baidu.com/"}]
             */

            private String recommendation_img;
            private String bind_type;
            private String bind_url;
            private String is_loop;
            private String course_id;
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

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }

            public List<LoopDatabean> getLoop_data() {
                return loop_data;
            }

            public void setLoop_data(List<LoopDatabean> loop_data) {
                this.loop_data = loop_data;
            }

            public static class LoopDatabean {
                /**
                 * recommendation_img : http://139.9.7.208:8989/Public/Uploads/images/2019-11-22/5dd7c57035ed2.jpg
                 * bind_type : 3
                 * bind_url : https://www.baidu.com/
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
    }
}
