package com.geling.view.gelingtv_XX_tongbu.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/4------更新------
 * 课程详情
 */

public class CourseDetailModel {


    /**
     * code : 10000
     * msg : 请求成功
     * data : {"course_id":"1050","course_name":"状元大课堂语文七年级","course_description":"《状元大课堂》经过全新策划，除了延续以往的精彩之外，将呈现出更加优良的品质来：更精的板块编排、更加实效的讲练内容、全新的名师真人微课堂，真正的备课导学案","hasOrderUrl":"/index.php?s=/Api/api/getHasOrderByVideoId.html","getVisionUrl":"http://api.itvengine.com.lv1.vcache.cn/itv-api/api/v1/search/boxversion.json","course_collection_status":0,"course_play_num":0,"course_play_unit":"次","course_teacher_name":"格灵教育高级讲师","video_list_data":[{"video_list_tag":"1-12","video_list":[{"video_id":"10347","video_name":"讲解,分析修辞手法的表达效果","is_free":"0","volumn_count":"1"},{"video_id":"10348","video_name":"讲解,怎样解答语句赏析题","is_free":"1","volumn_count":"2"},{"video_id":"10349","video_name":"讲解,炼字型古诗阅读题解答技巧","is_free":"1","volumn_count":"3"},{"video_id":"10350","video_name":"讲解,怎样理解事物的象征意义","is_free":"1","volumn_count":"4"},{"video_id":"10351","video_name":"讲解,如何概括内容要点","is_free":"1","volumn_count":"5"},{"video_id":"10352","video_name":"讲解,散文中的对比写法及其作用","is_free":"1","volumn_count":"6"},{"video_id":"10353","video_name":"讲解,如何理解文言虚词的意义和用法","is_free":"1","volumn_count":"7"},{"video_id":"10354","video_name":"讲解,如何寻找、归纳中心论点","is_free":"1","volumn_count":"8"},{"video_id":"10355","video_name":"讲解,怎样分析文章段落的作用","is_free":"1","volumn_count":"9"},{"video_id":"10356","video_name":"讲解,文言句子的朗读与停顿","is_free":"1","volumn_count":"10"},{"video_id":"10357","video_name":"讲解,分析人物形象","is_free":"1","volumn_count":"11"},{"video_id":"10358","video_name":"讲解,文言句子的翻译方法","is_free":"1","volumn_count":"12"}]},{"video_list_tag":"13-21","video_list":[{"video_id":"10359","video_name":"综合性学习微课,有朋自远方来","is_free":"1","volumn_count":"13"},{"video_id":"10360","video_name":"综合性学习微课,少年正是读书时","is_free":"1","volumn_count":"14"},{"video_id":"10361","video_name":"综合性学习微课,文学部落","is_free":"1","volumn_count":"15"},{"video_id":"10362","video_name":"作文讲解,热爱生活，热爱写作","is_free":"1","volumn_count":"16"},{"video_id":"10363","video_name":"作文讲解,学会记事","is_free":"1","volumn_count":"17"},{"video_id":"10364","video_name":"作文讲解,写人要抓住特点","is_free":"1","volumn_count":"18"},{"video_id":"10365","video_name":"作文讲解,思路要清晰","is_free":"1","volumn_count":"19"},{"video_id":"10366","video_name":"作文讲解,如何突出中心","is_free":"1","volumn_count":"20"},{"video_id":"10367","video_name":"作文讲解,发挥联想和想象","is_free":"1","volumn_count":"21"}]}],"total_pages":2,"total_count":21}
     */

    private int code;
    private String msg;
    private Databean data;

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

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * course_id : 1050
         * course_name : 状元大课堂语文七年级
         * course_description : 《状元大课堂》经过全新策划，除了延续以往的精彩之外，将呈现出更加优良的品质来：更精的板块编排、更加实效的讲练内容、全新的名师真人微课堂，真正的备课导学案
         * hasOrderUrl : /index.php?s=/Api/api/getHasOrderByVideoId.html
         * getVisionUrl : http://api.itvengine.com.lv1.vcache.cn/itv-api/api/v1/search/boxversion.json
         * course_collection_status : 0
         * course_play_num : 0
         * course_play_unit : 次
         * course_teacher_name : 格灵教育高级讲师
         * video_list_data : [{"video_list_tag":"1-12","video_list":[{"video_id":"10347","video_name":"讲解,分析修辞手法的表达效果","is_free":"0","volumn_count":"1"},{"video_id":"10348","video_name":"讲解,怎样解答语句赏析题","is_free":"1","volumn_count":"2"},{"video_id":"10349","video_name":"讲解,炼字型古诗阅读题解答技巧","is_free":"1","volumn_count":"3"},{"video_id":"10350","video_name":"讲解,怎样理解事物的象征意义","is_free":"1","volumn_count":"4"},{"video_id":"10351","video_name":"讲解,如何概括内容要点","is_free":"1","volumn_count":"5"},{"video_id":"10352","video_name":"讲解,散文中的对比写法及其作用","is_free":"1","volumn_count":"6"},{"video_id":"10353","video_name":"讲解,如何理解文言虚词的意义和用法","is_free":"1","volumn_count":"7"},{"video_id":"10354","video_name":"讲解,如何寻找、归纳中心论点","is_free":"1","volumn_count":"8"},{"video_id":"10355","video_name":"讲解,怎样分析文章段落的作用","is_free":"1","volumn_count":"9"},{"video_id":"10356","video_name":"讲解,文言句子的朗读与停顿","is_free":"1","volumn_count":"10"},{"video_id":"10357","video_name":"讲解,分析人物形象","is_free":"1","volumn_count":"11"},{"video_id":"10358","video_name":"讲解,文言句子的翻译方法","is_free":"1","volumn_count":"12"}]},{"video_list_tag":"13-21","video_list":[{"video_id":"10359","video_name":"综合性学习微课,有朋自远方来","is_free":"1","volumn_count":"13"},{"video_id":"10360","video_name":"综合性学习微课,少年正是读书时","is_free":"1","volumn_count":"14"},{"video_id":"10361","video_name":"综合性学习微课,文学部落","is_free":"1","volumn_count":"15"},{"video_id":"10362","video_name":"作文讲解,热爱生活，热爱写作","is_free":"1","volumn_count":"16"},{"video_id":"10363","video_name":"作文讲解,学会记事","is_free":"1","volumn_count":"17"},{"video_id":"10364","video_name":"作文讲解,写人要抓住特点","is_free":"1","volumn_count":"18"},{"video_id":"10365","video_name":"作文讲解,思路要清晰","is_free":"1","volumn_count":"19"},{"video_id":"10366","video_name":"作文讲解,如何突出中心","is_free":"1","volumn_count":"20"},{"video_id":"10367","video_name":"作文讲解,发挥联想和想象","is_free":"1","volumn_count":"21"}]}]
         * total_pages : 2
         * total_count : 21
         */

        private String course_id;
        private String course_name;
        private String course_description;
        private String hasOrderUrl;
        private String getVisionUrl;
        private int course_collection_status;
        private double course_play_num;
        private String course_play_unit;
        private String course_teacher_name;
        private int total_pages;
        private int total_count;
        private List<VideoListDatabean> video_list_data;

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_description() {
            return course_description;
        }

        public void setCourse_description(String course_description) {
            this.course_description = course_description;
        }

        public String getHasOrderUrl() {
            return hasOrderUrl;
        }

        public void setHasOrderUrl(String hasOrderUrl) {
            this.hasOrderUrl = hasOrderUrl;
        }

        public String getGetVisionUrl() {
            return getVisionUrl;
        }

        public void setGetVisionUrl(String getVisionUrl) {
            this.getVisionUrl = getVisionUrl;
        }

        public int getCourse_collection_status() {
            return course_collection_status;
        }

        public void setCourse_collection_status(int course_collection_status) {
            this.course_collection_status = course_collection_status;
        }

        public double getCourse_play_num() {
            return course_play_num;
        }

        public void setCourse_play_num(double course_play_num) {
            this.course_play_num = course_play_num;
        }

        public String getCourse_play_unit() {
            return course_play_unit;
        }

        public void setCourse_play_unit(String course_play_unit) {
            this.course_play_unit = course_play_unit;
        }

        public String getCourse_teacher_name() {
            return course_teacher_name;
        }

        public void setCourse_teacher_name(String course_teacher_name) {
            this.course_teacher_name = course_teacher_name;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public void setTotal_pages(int total_pages) {
            this.total_pages = total_pages;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public List<VideoListDatabean> getVideo_list_data() {
            return video_list_data;
        }

        public void setVideo_list_data(List<VideoListDatabean> video_list_data) {
            this.video_list_data = video_list_data;
        }

        public static class VideoListDatabean {
            /**
             * video_list_tag : 1-12
             * video_list : [{"video_id":"10347","video_name":"讲解,分析修辞手法的表达效果","is_free":"0","volumn_count":"1"},{"video_id":"10348","video_name":"讲解,怎样解答语句赏析题","is_free":"1","volumn_count":"2"},{"video_id":"10349","video_name":"讲解,炼字型古诗阅读题解答技巧","is_free":"1","volumn_count":"3"},{"video_id":"10350","video_name":"讲解,怎样理解事物的象征意义","is_free":"1","volumn_count":"4"},{"video_id":"10351","video_name":"讲解,如何概括内容要点","is_free":"1","volumn_count":"5"},{"video_id":"10352","video_name":"讲解,散文中的对比写法及其作用","is_free":"1","volumn_count":"6"},{"video_id":"10353","video_name":"讲解,如何理解文言虚词的意义和用法","is_free":"1","volumn_count":"7"},{"video_id":"10354","video_name":"讲解,如何寻找、归纳中心论点","is_free":"1","volumn_count":"8"},{"video_id":"10355","video_name":"讲解,怎样分析文章段落的作用","is_free":"1","volumn_count":"9"},{"video_id":"10356","video_name":"讲解,文言句子的朗读与停顿","is_free":"1","volumn_count":"10"},{"video_id":"10357","video_name":"讲解,分析人物形象","is_free":"1","volumn_count":"11"},{"video_id":"10358","video_name":"讲解,文言句子的翻译方法","is_free":"1","volumn_count":"12"}]
             */

            private String video_list_tag;
            private List<VideoListbean> video_list;

            public String getVideo_list_tag() {
                return video_list_tag;
            }

            public void setVideo_list_tag(String video_list_tag) {
                this.video_list_tag = video_list_tag;
            }

            public List<VideoListbean> getVideo_list() {
                return video_list;
            }

            public void setVideo_list(List<VideoListbean> video_list) {
                this.video_list = video_list;
            }

            public static class VideoListbean {
                /**
                 * video_id : 10347
                 * video_name : 讲解,分析修辞手法的表达效果
                 * is_free : 0
                 * volumn_count : 1
                 */

                private String video_id;
                private String video_name;
                private String is_free;//0表示免费
                private String volumn_count;

                public String getVideo_id() {
                    return video_id;
                }

                public void setVideo_id(String video_id) {
                    this.video_id = video_id;
                }

                public String getVideo_name() {
                    return video_name;
                }

                public void setVideo_name(String video_name) {
                    this.video_name = video_name;
                }

                public String getIs_free() {
                    return is_free;
                }

                public void setIs_free(String is_free) {
                    this.is_free = is_free;
                }

                public String getVolumn_count() {
                    return volumn_count;
                }

                public void setVolumn_count(String volumn_count) {
                    this.volumn_count = volumn_count;
                }
            }
        }
    }
}
