package com.geling.view.gelingtv_XX_tongbu.ui.event;

public class EventPlayReocrdMassge {
    private String Tapy;
    private String courseId;
    /**
     * @Tapy update_phone 修改手机号码
     * */
    public String getTapy() {
        return Tapy;
    }

    public void setTapy(String tapy) {
        this.Tapy = tapy;
    }

    public String getCourseId() {
        return courseId;
    }
    public EventPlayReocrdMassge(String courseId,String tapy){
        this.courseId = courseId;
        this.Tapy = tapy;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
