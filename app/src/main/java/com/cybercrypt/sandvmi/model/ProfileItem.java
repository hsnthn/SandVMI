
package com.cybercrypt.sandvmi.model;

public class ProfileItem {

    private String profileItem;
    private int imgResID;

    public ProfileItem(String profileItem, int imgResID) {
        this.profileItem = profileItem;
        this.imgResID = imgResID;
    }

    public String getProfileItem() {
        return profileItem;
    }

    public void setProfileItem(String profileItem) {
        this.profileItem = profileItem;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
}