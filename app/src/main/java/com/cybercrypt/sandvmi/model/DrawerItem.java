package com.cybercrypt.sandvmi.model;

public class DrawerItem {

    private String ItemName;
    private int imgResID;
    private String layout_tag;

    public DrawerItem(String itemName, int imgResID, String layout_tag) {
        ItemName = itemName;
        this.imgResID = imgResID;
        this.layout_tag = layout_tag;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

    public String getLayout_tag() {
        return layout_tag;
    }

    public void setLayout_tag(String layout_tag) {
        this.layout_tag = layout_tag;
    }
}