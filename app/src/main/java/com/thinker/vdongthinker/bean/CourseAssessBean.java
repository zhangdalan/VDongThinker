package com.thinker.vdongthinker.bean;

/**
 * Created by zt on 2018/12/13.
 */

public class CourseAssessBean {
    private String img;
    private String name;
    private String date;
    private String content;

    public CourseAssessBean(String img, String name, String date, String content) {
        this.img = img;
        this.name = name;
        this.date = date;
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
