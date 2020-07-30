package com.geling.view.gelingtv_XX_tongbu.model.bean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/11/8------更新------
 * 搜索界面数据
 */

public class SearchModel {

    /**
     * code : 10000
     * msg : 请求成功
     * data : {"page_data":[[{"course_id":"83","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongwulibixiu2.png","view_num":"0"},{"course_id":"2679","course_img":"http://iptv3.com/Public/Uploads/images/2019-10-21/5dad20ac4824c.jpg","view_num":"0"},{"course_id":"109","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongzhengzhibixiu4.png","view_num":"0"},{"course_id":"108","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongzhengzhibixiu3.png","view_num":"0"},{"course_id":"107","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongzhengzhibixiu2.png","view_num":"0"},{"course_id":"106","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongzhengzhibixiu1.png","view_num":"0"},{"course_id":"100","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongdilibixiu3.png","view_num":"0"},{"course_id":"99","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongdilibixiu2.png","view_num":"0"},{"course_id":"98","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongdilibixiu1.png","view_num":"0"},{"course_id":"96","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonglishibixiu2.png","view_num":"0"},{"course_id":"95","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonglishibixiu1.png","view_num":"0"},{"course_id":"89","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshengwu3ce.png","view_num":"0"}],[{"course_id":"88","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshengwubixiu2.png","view_num":"0"},{"course_id":"87","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshengwu1ce.png","view_num":"0"},{"course_id":"86","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonghuaxuebixiu2.png","view_num":"0"},{"course_id":"85","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonghuaxuebixiu1.png","view_num":"0"},{"course_id":"47","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban6yuwenbixiu1.png","view_num":"0"},{"course_id":"82","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongwulibixiu1.png","view_num":"0"},{"course_id":"79","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu5yingyu.png","view_num":"0"},{"course_id":"78","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu4yingyu.png","view_num":"0"},{"course_id":"77","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu3yingyu.png","view_num":"0"},{"course_id":"76","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu2yingyu.png","view_num":"0"},{"course_id":"75","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu1yingyu.png","view_num":"0"},{"course_id":"65","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobanshuxuebixiu5.png","view_num":"0"}],[{"course_id":"64","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobanshuxuebixiu4.png","view_num":"0"},{"course_id":"63","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshuxuebixiu3.png","view_num":"0"},{"course_id":"61","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshuxuebixiu1.png","view_num":"0"},{"course_id":"51","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongyuwenbixiu5.png","view_num":"0"},{"course_id":"50","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongyuwenbixiu4.png","view_num":"0"},{"course_id":"49","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban6yuwenbixiu3.png","view_num":"0"}]],"total_count":30,"total_pages":3}
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
         * page_data : [[{"course_id":"83","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongwulibixiu2.png","view_num":"0"},{"course_id":"2679","course_img":"http://iptv3.com/Public/Uploads/images/2019-10-21/5dad20ac4824c.jpg","view_num":"0"},{"course_id":"109","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongzhengzhibixiu4.png","view_num":"0"},{"course_id":"108","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongzhengzhibixiu3.png","view_num":"0"},{"course_id":"107","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongzhengzhibixiu2.png","view_num":"0"},{"course_id":"106","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongzhengzhibixiu1.png","view_num":"0"},{"course_id":"100","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongdilibixiu3.png","view_num":"0"},{"course_id":"99","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongdilibixiu2.png","view_num":"0"},{"course_id":"98","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongdilibixiu1.png","view_num":"0"},{"course_id":"96","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonglishibixiu2.png","view_num":"0"},{"course_id":"95","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonglishibixiu1.png","view_num":"0"},{"course_id":"89","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshengwu3ce.png","view_num":"0"}],[{"course_id":"88","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshengwubixiu2.png","view_num":"0"},{"course_id":"87","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshengwu1ce.png","view_num":"0"},{"course_id":"86","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonghuaxuebixiu2.png","view_num":"0"},{"course_id":"85","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhonghuaxuebixiu1.png","view_num":"0"},{"course_id":"47","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban6yuwenbixiu1.png","view_num":"0"},{"course_id":"82","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongwulibixiu1.png","view_num":"0"},{"course_id":"79","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu5yingyu.png","view_num":"0"},{"course_id":"78","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu4yingyu.png","view_num":"0"},{"course_id":"77","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu3yingyu.png","view_num":"0"},{"course_id":"76","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu2yingyu.png","view_num":"0"},{"course_id":"75","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobangaozhongbixiu1yingyu.png","view_num":"0"},{"course_id":"65","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobanshuxuebixiu5.png","view_num":"0"}],[{"course_id":"64","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaobanshuxuebixiu4.png","view_num":"0"},{"course_id":"63","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshuxuebixiu3.png","view_num":"0"},{"course_id":"61","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongshuxuebixiu1.png","view_num":"0"},{"course_id":"51","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongyuwenbixiu5.png","view_num":"0"},{"course_id":"50","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongyuwenbixiu4.png","view_num":"0"},{"course_id":"49","course_img":"http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban6yuwenbixiu3.png","view_num":"0"}]]
         * total_count : 30
         * total_pages : 3
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
             * course_id : 83
             * course_img : http://iptv3.com/Public/Uploads/images/2017-04-26/zhongxuetongbujingjiang-renjiaoban-gaozhongwulibixiu2.png
             * view_num : 0
             */

            private String course_id;
            private String course_img;
            private String view_num;

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

            public String getView_num() {
                return view_num;
            }

            public void setView_num(String view_num) {
                this.view_num = view_num;
            }
        }
    }
}
