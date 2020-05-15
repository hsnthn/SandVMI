package com.cybercrypt.sandvmi.data.local.model;

public class SettingsItem {

    private String settingItem;
    private String settingdef;
    private int imgResID;

    public SettingsItem(String settingItem, String settingdef, int imgResID) {
        this.settingItem = settingItem;
        this.settingdef = settingdef;
        this.imgResID = imgResID;
    }

    public String getSettingItem() {
        return settingItem;
    }

    public void setSettingItem(String settingItem) {
        this.settingItem = settingItem;
    }

    public String getSettingdef() {
        return settingdef;
    }

    public void setSettingdef(String settingdef) {
        this.settingdef = settingdef;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
}
