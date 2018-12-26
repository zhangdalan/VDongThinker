package com.thinker.vdongthinker.bean;

/**
 *
 */

public class CourseIconBean {
    private int url;
    private String name;

    public CourseIconBean(int url, String name) {
        this.url = url;
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
