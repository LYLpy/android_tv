package com.geling.view.gelingtv_XX_tongbu.model.bean.pybean;

import java.util.List;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2020/2/22------更新------
 * 搜索的bean
 */

public class SearchModelPy {

    /**
     * error : 10000
     * success : success
     * status : 200
     * msg : 请求成功
     * data : {"total":11,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":2921,"name":"教科版广州小学英语三起三年级上册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f3e3c32f072fbe707ce3bd09a05c6b96.png"},{"id":2922,"name":"教科版广州小学英语三起四年级上册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/751e3166f807f043e4eb782529a1f478.png"},{"id":2923,"name":"教科版广州小学英语三起五年级上册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/7638cdc0784ccd52a4373b2c935f0f00.png"},{"id":2924,"name":"教科版广州小学英语三起六年级上册","icon":"http://images.gelearning.net/uploads/"},{"id":6561,"name":"教科版广州小学英语三起三年级下册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/98efa0ec92f4a891efe3a537a364e286.png"},{"id":6562,"name":"教科版广州小学英语三起四年级下册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/c9a623ffa9814a995c4bb618bd218d61.png"},{"id":6563,"name":"教科版广州小学英语三起六年级下册","icon":"http://images.gelearning.net/uploads/"},{"id":8746,"name":"教科版广州小学英语三起五年级下册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f9e9f7fd769642e52267e8cbaa50638c.png"},{"id":8768,"name":"人教部编版小学语文四年级下册（更新中）","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/8026665ad8054eb64b66cfc4d2abb33d.png"},{"id":8769,"name":"人教部编版小学语文六年级下册（更新中）","icon":"http://images.gelearning.net/uploads/"},{"id":8771,"name":"人教部编版小学语文五年级下册（更新中）","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/de1ec6e84b93b251e08714f63d6b50ae.png"}]}
     */

    private int error;
    private String success;
    private String status;
    private String msg;
    private DatabeanX data;

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

    public DatabeanX getData() {
        return data;
    }

    public void setData(DatabeanX data) {
        this.data = data;
    }

    public static class DatabeanX {
        /**
         * total : 11
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":2921,"name":"教科版广州小学英语三起三年级上册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f3e3c32f072fbe707ce3bd09a05c6b96.png"},{"id":2922,"name":"教科版广州小学英语三起四年级上册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/751e3166f807f043e4eb782529a1f478.png"},{"id":2923,"name":"教科版广州小学英语三起五年级上册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/7638cdc0784ccd52a4373b2c935f0f00.png"},{"id":2924,"name":"教科版广州小学英语三起六年级上册","icon":"http://images.gelearning.net/uploads/"},{"id":6561,"name":"教科版广州小学英语三起三年级下册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/98efa0ec92f4a891efe3a537a364e286.png"},{"id":6562,"name":"教科版广州小学英语三起四年级下册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/c9a623ffa9814a995c4bb618bd218d61.png"},{"id":6563,"name":"教科版广州小学英语三起六年级下册","icon":"http://images.gelearning.net/uploads/"},{"id":8746,"name":"教科版广州小学英语三起五年级下册","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/f9e9f7fd769642e52267e8cbaa50638c.png"},{"id":8768,"name":"人教部编版小学语文四年级下册（更新中）","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/8026665ad8054eb64b66cfc4d2abb33d.png"},{"id":8769,"name":"人教部编版小学语文六年级下册（更新中）","icon":"http://images.gelearning.net/uploads/"},{"id":8771,"name":"人教部编版小学语文五年级下册（更新中）","icon":"http://test-xxtbpy.getlearn.cn/uploads/20190903/de1ec6e84b93b251e08714f63d6b50ae.png"}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<Databean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<Databean> getData() {
            return data;
        }

        public void setData(List<Databean> data) {
            this.data = data;
        }

        public static class Databean {
            /**
             * id : 2921
             * name : 教科版广州小学英语三起三年级上册
             * icon : http://test-xxtbpy.getlearn.cn/uploads/20190903/f3e3c32f072fbe707ce3bd09a05c6b96.png
             */

            private int id;
            private String name;
            private String icon;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
