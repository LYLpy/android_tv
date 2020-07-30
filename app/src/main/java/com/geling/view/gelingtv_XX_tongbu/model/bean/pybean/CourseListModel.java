package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/20------更新------
 * 课程列表界面数据bean
 */

public class CourseListModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"teacher":[{"head_img_url":"","teacher":"陈剑","teacher_introduce":"北京航空航天大学毕业，曾任海淀妇幼早教老师，现任于广州某知名教育机构。调动了同学们的积极性，激发了其对知识的好奇和渴望，注意知识与方法的讲解，知识丰富，善于生动、细致讲课，深入剖析知识点，学生易于理解、掌握。","teacher_id":567,"position":"优秀教师","lable":[""]}],"course_info":{"id":6023,"name":"人教部编版小学语文一年级上册","content":"","click":3912,"collection":0},"class_list":[{"id":1,"name":"1 天地人","courseId":6023,"is_free":0,"history":0},{"id":2,"name":"2 金木水土火","courseId":6023,"is_free":0,"history":0},{"id":3,"name":"3 口耳目","courseId":6023,"is_free":1,"history":0},{"id":4,"name":"4 日月水火","courseId":6023,"is_free":1,"history":0},{"id":5,"name":"5 对韵歌","courseId":6023,"is_free":1,"history":0},{"id":6,"name":"6 画","courseId":6023,"is_free":1,"history":0},{"id":7,"name":"7 大小多少","courseId":6023,"is_free":1,"history":0},{"id":8,"name":"8 小书包","courseId":6023,"is_free":1,"history":0},{"id":9,"name":"9 日月明","courseId":6023,"is_free":1,"history":0},{"id":10,"name":"10 升国旗","courseId":6023,"is_free":1,"history":0},{"id":11,"name":"1 a o e","courseId":6023,"is_free":1,"history":0},{"id":12,"name":"2 i u v y w","courseId":6023,"is_free":1,"history":0},{"id":13,"name":"3 b p m f","courseId":6023,"is_free":1,"history":0},{"id":14,"name":"4 d t n l","courseId":6023,"is_free":1,"history":0},{"id":15,"name":"5 g k h","courseId":6023,"is_free":1,"history":0},{"id":16,"name":"6 j q x","courseId":6023,"is_free":1,"history":0},{"id":17,"name":"7 z c s","courseId":6023,"is_free":1,"history":0},{"id":18,"name":"8 zh ch sh r","courseId":6023,"is_free":1,"history":0},{"id":19,"name":"9 ai ei ui","courseId":6023,"is_free":1,"history":0},{"id":20,"name":"10 ao ou iu","courseId":6023,"is_free":1,"history":0},{"id":21,"name":"11 ie üe er","courseId":6023,"is_free":1,"history":0},{"id":22,"name":"12 an en in un ün","courseId":6023,"is_free":1,"history":0},{"id":23,"name":"13 ang eng ing ong","courseId":6023,"is_free":1,"history":0},{"id":24,"name":"1 秋天","courseId":6023,"is_free":1,"history":0},{"id":25,"name":"2 小小的船","courseId":6023,"is_free":1,"history":0},{"id":26,"name":"3 江南","courseId":6023,"is_free":1,"history":0},{"id":27,"name":"4 四季","courseId":6023,"is_free":1,"history":0},{"id":28,"name":"5 影子","courseId":6023,"is_free":1,"history":0},{"id":29,"name":"6 比尾巴","courseId":6023,"is_free":1,"history":0},{"id":30,"name":"7 青蛙写诗","courseId":6023,"is_free":1,"history":0},{"id":31,"name":"8 雨点儿","courseId":6023,"is_free":1,"history":0},{"id":32,"name":"9 明天要远足","courseId":6023,"is_free":1,"history":0},{"id":33,"name":"10 大还是小","courseId":6023,"is_free":1,"history":0},{"id":34,"name":"11 项链","courseId":6023,"is_free":1,"history":0},{"id":35,"name":"12 雪地里的小画家","courseId":6023,"is_free":1,"history":0},{"id":36,"name":"13 乌鸦喝水","courseId":6023,"is_free":1,"history":0},{"id":37,"name":"14 小蜗牛","courseId":6023,"is_free":1,"history":0}]}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private Databean data;

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

    public Databean getData() {
        return data;
    }

    public void setData(Databean data) {
        this.data = data;
    }

    public static class Databean {
        /**
         * teacher : [{"head_img_url":"","teacher":"陈剑","teacher_introduce":"北京航空航天大学毕业，曾任海淀妇幼早教老师，现任于广州某知名教育机构。调动了同学们的积极性，激发了其对知识的好奇和渴望，注意知识与方法的讲解，知识丰富，善于生动、细致讲课，深入剖析知识点，学生易于理解、掌握。","teacher_id":567,"position":"优秀教师","lable":[""]}]
         * course_info : {"id":6023,"name":"人教部编版小学语文一年级上册","content":"","click":3912,"collection":0}
         * class_list : [{"id":1,"name":"1 天地人","courseId":6023,"is_free":0,"history":0},{"id":2,"name":"2 金木水土火","courseId":6023,"is_free":0,"history":0},{"id":3,"name":"3 口耳目","courseId":6023,"is_free":1,"history":0},{"id":4,"name":"4 日月水火","courseId":6023,"is_free":1,"history":0},{"id":5,"name":"5 对韵歌","courseId":6023,"is_free":1,"history":0},{"id":6,"name":"6 画","courseId":6023,"is_free":1,"history":0},{"id":7,"name":"7 大小多少","courseId":6023,"is_free":1,"history":0},{"id":8,"name":"8 小书包","courseId":6023,"is_free":1,"history":0},{"id":9,"name":"9 日月明","courseId":6023,"is_free":1,"history":0},{"id":10,"name":"10 升国旗","courseId":6023,"is_free":1,"history":0},{"id":11,"name":"1 a o e","courseId":6023,"is_free":1,"history":0},{"id":12,"name":"2 i u v y w","courseId":6023,"is_free":1,"history":0},{"id":13,"name":"3 b p m f","courseId":6023,"is_free":1,"history":0},{"id":14,"name":"4 d t n l","courseId":6023,"is_free":1,"history":0},{"id":15,"name":"5 g k h","courseId":6023,"is_free":1,"history":0},{"id":16,"name":"6 j q x","courseId":6023,"is_free":1,"history":0},{"id":17,"name":"7 z c s","courseId":6023,"is_free":1,"history":0},{"id":18,"name":"8 zh ch sh r","courseId":6023,"is_free":1,"history":0},{"id":19,"name":"9 ai ei ui","courseId":6023,"is_free":1,"history":0},{"id":20,"name":"10 ao ou iu","courseId":6023,"is_free":1,"history":0},{"id":21,"name":"11 ie üe er","courseId":6023,"is_free":1,"history":0},{"id":22,"name":"12 an en in un ün","courseId":6023,"is_free":1,"history":0},{"id":23,"name":"13 ang eng ing ong","courseId":6023,"is_free":1,"history":0},{"id":24,"name":"1 秋天","courseId":6023,"is_free":1,"history":0},{"id":25,"name":"2 小小的船","courseId":6023,"is_free":1,"history":0},{"id":26,"name":"3 江南","courseId":6023,"is_free":1,"history":0},{"id":27,"name":"4 四季","courseId":6023,"is_free":1,"history":0},{"id":28,"name":"5 影子","courseId":6023,"is_free":1,"history":0},{"id":29,"name":"6 比尾巴","courseId":6023,"is_free":1,"history":0},{"id":30,"name":"7 青蛙写诗","courseId":6023,"is_free":1,"history":0},{"id":31,"name":"8 雨点儿","courseId":6023,"is_free":1,"history":0},{"id":32,"name":"9 明天要远足","courseId":6023,"is_free":1,"history":0},{"id":33,"name":"10 大还是小","courseId":6023,"is_free":1,"history":0},{"id":34,"name":"11 项链","courseId":6023,"is_free":1,"history":0},{"id":35,"name":"12 雪地里的小画家","courseId":6023,"is_free":1,"history":0},{"id":36,"name":"13 乌鸦喝水","courseId":6023,"is_free":1,"history":0},{"id":37,"name":"14 小蜗牛","courseId":6023,"is_free":1,"history":0}]
         */

        private CourseInfobean course_info;
        private List<Teacherbean> teacher;
        private List<ClassListbean> class_list;

        public CourseInfobean getCourse_info() {
            return course_info;
        }

        public void setCourse_info(CourseInfobean course_info) {
            this.course_info = course_info;
        }

        public List<Teacherbean> getTeacher() {
            return teacher;
        }

        public void setTeacher(List<Teacherbean> teacher) {
            this.teacher = teacher;
        }

        public List<ClassListbean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListbean> class_list) {
            this.class_list = class_list;
        }

        public static class CourseInfobean {
            /**
             * id : 6023
             * name : 人教部编版小学语文一年级上册
             * content :
             * click : 3912
             * collection : 0
             */

            private int id;
            private String name;
            private String content;
            private int click;
            private int collection;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getClick() {
                return click;
            }

            public void setClick(int click) {
                this.click = click;
            }

            public int getCollection() {
                return collection;
            }

            public void setCollection(int collection) {
                this.collection = collection;
            }
        }

        public static class Teacherbean {
            /**
             * head_img_url :
             * teacher : 陈剑
             * teacher_introduce : 北京航空航天大学毕业，曾任海淀妇幼早教老师，现任于广州某知名教育机构。调动了同学们的积极性，激发了其对知识的好奇和渴望，注意知识与方法的讲解，知识丰富，善于生动、细致讲课，深入剖析知识点，学生易于理解、掌握。
             * teacher_id : 567
             * position : 优秀教师
             * lable : [""]
             */

            private String head_img_url;
            private String teacher;
            private String teacher_introduce;
            private int teacher_id;
            private String position;
            private List<String> lable;

            public String getHead_img_url() {
                return head_img_url;
            }

            public void setHead_img_url(String head_img_url) {
                this.head_img_url = head_img_url;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }

            public String getTeacher_introduce() {
                return teacher_introduce;
            }

            public void setTeacher_introduce(String teacher_introduce) {
                this.teacher_introduce = teacher_introduce;
            }

            public int getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(int teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public List<String> getLable() {
                return lable;
            }

            public void setLable(List<String> lable) {
                this.lable = lable;
            }
        }

        public static class ClassListbean {
            /**
             * id : 1
             * name : 1 天地人
             * courseId : 6023
             * is_free : 0
             * history : 0
             */

            private int id;
            private String name;
            private int courseId;
            private int is_free;//0为免费，1为收费
            private int history;

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

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public int getIs_free() {
                return is_free;
            }

            public void setIs_free(int is_free) {
                this.is_free = is_free;
            }

            public int getHistory() {
                return history;
            }

            public void setHistory(int history) {
                this.history = history;
            }
        }
    }
}
