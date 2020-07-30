package com.geling.view.gelingtv_XX_tongbu.model.bean;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/10/21------更新------
 */

public class MainNavModel {

    private String name;
    private int selectedIconId;//选中icon的id
    private int normalIconId;//正常icon的id
    private int focusIconId;//聚焦icon的id
    private int index;//下标，首页导航栏固定15个item,下标指这是第几个
    private String selectedIconUrl;//选中icon的Url
    private String normalIconUrl;//正常icon的Url
    private String focusIconUrl;//聚焦icon的Url

    public MainNavModel(String name, int normalIconId, int selectedIconId, int focusIconId, int index) {
        this.name = name;
        this.selectedIconId = selectedIconId;
        this.normalIconId = normalIconId;
        this.focusIconId = focusIconId;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getFocusIconId() {
        return focusIconId;
    }

    public void setFocusIconId(int focusIconId) {
        this.focusIconId = focusIconId;
    }

    public int getSelectedIconId() {
        return selectedIconId;
    }

    public void setSelectedIconId(int selectedIconId) {
        this.selectedIconId = selectedIconId;
    }

    public int getNormalIconId() {
        return normalIconId;
    }

    public void setNormalIconId(int normalIconId) {
        this.normalIconId = normalIconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectedIconUrl() {
        return selectedIconUrl;
    }

    public void setSelectedIconUrl(String selectedIconUrl) {
        this.selectedIconUrl = selectedIconUrl;
    }

    public String getNormalIconUrl() {
        return normalIconUrl;
    }

    public void setNormalIconUrl(String normalIconUrl) {
        this.normalIconUrl = normalIconUrl;
    }

    public String getFocusIconUrl() {
        return focusIconUrl;
    }

    public void setFocusIconUrl(String focusIconUrl) {
        this.focusIconUrl = focusIconUrl;
    }
}
