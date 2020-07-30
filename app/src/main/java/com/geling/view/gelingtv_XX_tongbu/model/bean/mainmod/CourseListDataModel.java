package com.geling.view.gelingtv_XX_tongbu.model.bean.mainmod;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/29------更新------
 */

public class CourseListDataModel {
    private List<CourseListNav> mListNavs;
    private List<CourseListPage> mListPages;

    public CourseListDataModel(List<CourseListNav> listNavs, List<CourseListPage> listPages) {
        mListNavs = listNavs;
        mListPages = listPages;
    }

    public List<CourseListNav> getListNavs() {
        return mListNavs;
    }

    public void setListNavs(List<CourseListNav> listNavs) {
        mListNavs = listNavs;
    }

    public List<CourseListPage> getListPages() {
        return mListPages;
    }

    public void setListPages(List<CourseListPage> listPages) {
        mListPages = listPages;
    }

    public static class CourseListNav{

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
    public static class CourseListPage{

        /**
         * page_data : [[{"course_id":"1050","course_img":"http://iptv3.com/Public/Uploads/images/2018-09-16/5b9e19ba1abf0.png"},{"course_id":"1053","course_img":"http://iptv3.com/Public/Uploads/images/2018-09-16/5b9e1a4c2a398.png"}]]
         * left_nav_id : 24
         * total_count : 2
         * total_pages : 1
         */

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
             * course_id : 1050
             * course_img : http://iptv3.com/Public/Uploads/images/2018-09-16/5b9e19ba1abf0.png
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
