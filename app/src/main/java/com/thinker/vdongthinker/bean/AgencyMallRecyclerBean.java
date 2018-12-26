package com.thinker.vdongthinker.bean;

/**
 *
 */

public class AgencyMallRecyclerBean {
    private String title;
    private String address;
    private String num;

    public AgencyMallRecyclerBean(String title, String address, String num) {
        this.title = title;
        this.address = address;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}

