package com.cybercrypt.sandvmi.data.local.model;

public class VMListItem {

    private String vmName;
    private String lastVisited;
    private String vmUrl;
    private int imResID;

    public VMListItem(String vmName, String lastVisited, String vmUrl, int imResID) {
        this.vmName = vmName;
        this.lastVisited = lastVisited;
        this.vmUrl = vmUrl;
        this.imResID = imResID;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public String getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(String lastVisited) {
        this.lastVisited = lastVisited;
    }

    public int getImResID() {
        return imResID;
    }

    public void setImResID(int imResID) {
        this.imResID = imResID;
    }

    public String getVmUrl() {
        return vmUrl;
    }

    public void setVmUrl(String vmUrl) {
        this.vmUrl = vmUrl;
    }
}
