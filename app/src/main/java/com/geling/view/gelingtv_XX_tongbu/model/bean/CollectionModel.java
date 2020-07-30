package com.geling.view.gelingtv_XX_tongbu.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/2------更新------
 */

public class CollectionModel {

    /**
     * code : 10000
     * msg : 请求成功
     * data : {"page_data":[[{"course_id":"2450","course_name":"小伶玩具第二季","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e86a51326.jpg","category_name":"艺术创想"},{"course_id":"2444","course_name":"晓鹿甜品店","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e94139e11.jpg","category_name":"艺术创想"},{"course_id":"2451","course_name":"小伶玩具第三季","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e88f0eb37.jpg","category_name":"艺术创想"},{"course_id":"2432","course_name":"圆小米手工课","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e9617a93a.jpg","category_name":"艺术创想"},{"course_id":"2369","course_name":"萌宠养成","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e4ecce3ce.jpg","category_name":"艺术创想"},{"course_id":"2367","course_name":"日本食玩","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e6ceb1389.jpg","category_name":"艺术创想"}],[{"course_id":"2373","course_name":"创意大爆炸","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e2f9e9ec1.jpg","category_name":"艺术创想"},{"course_id":"2427","course_name":"小手动动动","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e8fe2ca9e.jpg","category_name":"艺术创想"},{"course_id":"2441","course_name":"手工乐园","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e71ac696e.jpg","category_name":"艺术创想"},{"course_id":"2445","course_name":"折纸乐园","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e979301f3.jpg","category_name":"艺术创想"},{"course_id":"2433","course_name":"美食课","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e4a15bed4.jpg","category_name":"艺术创想"},{"course_id":"2449","course_name":"小伶玩具第一季","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e8bbc97c0.jpg","category_name":"艺术创想"}]],"total_count":12,"total_pages":2}
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
         * page_data : [[{"course_id":"2450","course_name":"小伶玩具第二季","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e86a51326.jpg","category_name":"艺术创想"},{"course_id":"2444","course_name":"晓鹿甜品店","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e94139e11.jpg","category_name":"艺术创想"},{"course_id":"2451","course_name":"小伶玩具第三季","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e88f0eb37.jpg","category_name":"艺术创想"},{"course_id":"2432","course_name":"圆小米手工课","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e9617a93a.jpg","category_name":"艺术创想"},{"course_id":"2369","course_name":"萌宠养成","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e4ecce3ce.jpg","category_name":"艺术创想"},{"course_id":"2367","course_name":"日本食玩","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e6ceb1389.jpg","category_name":"艺术创想"}],[{"course_id":"2373","course_name":"创意大爆炸","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e2f9e9ec1.jpg","category_name":"艺术创想"},{"course_id":"2427","course_name":"小手动动动","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e8fe2ca9e.jpg","category_name":"艺术创想"},{"course_id":"2441","course_name":"手工乐园","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e71ac696e.jpg","category_name":"艺术创想"},{"course_id":"2445","course_name":"折纸乐园","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e979301f3.jpg","category_name":"艺术创想"},{"course_id":"2433","course_name":"美食课","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e4a15bed4.jpg","category_name":"艺术创想"},{"course_id":"2449","course_name":"小伶玩具第一季","course_img":"http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e8bbc97c0.jpg","category_name":"艺术创想"}]]
         * total_count : 12
         * total_pages : 2
         */

        private int total_count;
        private int total_pages;
        private List<List<PageDatabean>> page_data;

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
             * course_id : 2450
             * course_name : 小伶玩具第二季
             * course_img : http://iptv3.com/Public/Uploads/images/2018-08-06/5b67e86a51326.jpg
             * category_name : 艺术创想
             */

            private String course_id;
            private String course_name;
            private String course_img;
            private String category_name;

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

            public String getCourse_img() {
                return course_img;
            }

            public void setCourse_img(String course_img) {
                this.course_img = course_img;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }
        }
    }
}
