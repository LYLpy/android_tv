package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/18------更新------
 * 启动页
 */

public class StartModel {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"logo":"http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2019-11-27/5dde365556f4b.png","open_status":1,"open_time":5,"open_img":"http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2019-11-22/5dd7575d40b7a.jpg"}
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
         * logo : http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2019-11-27/5dde365556f4b.png
         * open_status : 1
         * open_time : 5
         * open_img : http://test-xxtbpy.getlearn.cn/uploads//Public/Uploads/images/2019-11-22/5dd7575d40b7a.jpg
         */

        private String logo;
        private int open_status;
        private int open_time;
        private String open_img;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getOpen_status() {
            return open_status;
        }

        public void setOpen_status(int open_status) {
            this.open_status = open_status;
        }

        public int getOpen_time() {
            return open_time;
        }

        public void setOpen_time(int open_time) {
            this.open_time = open_time;
        }

        public String getOpen_img() {
            return open_img;
        }

        public void setOpen_img(String open_img) {
            this.open_img = open_img;
        }
    }
}
