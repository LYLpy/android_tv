package com.geling.view.gelingtv_XX_tongbu.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/9------更新------
 * 视频Url的Bean
 */

public class VideoUrlBean {

    private String time;
    //以下这几个参数都是调用创维RTSP平台点播播放器需要用到的参数
    private String vodId;
    private String vodName;
    private String reportUrl;
    private String url;
    //实际的播放地址，自己播放器要用到的
    private String realUrl;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }

    public String getVodName() {
        return vodName;
    }

    public void setVodName(String vodName) {
        this.vodName = vodName;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }
}
