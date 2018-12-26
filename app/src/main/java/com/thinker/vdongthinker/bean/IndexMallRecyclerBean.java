package com.thinker.vdongthinker.bean;

/**
 *
 */

public class IndexMallRecyclerBean {
    private String title;
    private String price;
    private String num;

    public IndexMallRecyclerBean(String title, String price, String num) {
        this.title = title;
        this.price = price;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
