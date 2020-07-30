package com.geling.view.gelingtv_XX_tongbu.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/2------更新------
 * 历史分页数据
 */

public class HistoryPagingModel {

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

        private int split_num;
        private int start_page_num;
        private int total_count;
        private int total_pages;
        private List<List<PageDatabean>> page_data;

        public int getSplit_num() {
            return split_num;
        }

        public void setSplit_num(int split_num) {
            this.split_num = split_num;
        }

        public int getStart_page_num() {
            return start_page_num;
        }

        public void setStart_page_num(int start_page_num) {
            this.start_page_num = start_page_num;
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
             * video_id : 22
             * is_free : 1
             * video_name :  12 an en in un vn
             * course_name : 人教版小学语文1年级上册
             * course_img : http://10.60.33.178:8081/Public/Uploads/images/2019-12-24/5e01c8a4a5b86.jpg
             * play_date : 2019年12月28日 星期六
             */

            private String video_id;
            private String is_free;
            private String video_name;
            private String course_name;
            private String course_img;
            private String play_date;

            public String getVideo_id() {
                return video_id;
            }

            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }

            public String getIs_free() {
                return is_free;
            }

            public void setIs_free(String is_free) {
                this.is_free = is_free;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getCourse_name() {
                return course_name;
            }

            public void setCourse_name(String course_name) {
                this.course_name = course_name;
            }

            public String getCourse_img() {
                return course_img;
            }

            public void setCourse_img(String course_img) {
                this.course_img = course_img;
            }

            public String getPlay_date() {
                return play_date;
            }

            public void setPlay_date(String play_date) {
                this.play_date = play_date;
            }
        }
    }
}
