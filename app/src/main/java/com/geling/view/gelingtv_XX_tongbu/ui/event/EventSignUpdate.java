package com.geling.view.gelingtv_XX_tongbu.ui.event;

import org.greenrobot.eventbus.EventBus;

public class EventSignUpdate {
    private String  TAG;
    private String  Masg;

    public EventSignUpdate(String TAG,String Masg){
        this.TAG = TAG;
        this.Masg = Masg;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public String getMasg() {
        return Masg;
    }

    public void setMasg(String masg) {
        Masg = masg;
    }
}
