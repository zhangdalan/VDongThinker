package com.thinker.vdongthinker.bean;

/**
 * Created by zjw on 2018/12/26.
 */
public class MyCourseBean  {
    private String agency_name;
    private String is_study;
    private String img;
    private String course_name;
    private String sign;
    private String price;

    public MyCourseBean() {
    }

    public MyCourseBean(String agency_name, String is_study, String img, String course_name, String sign, String price) {
        this.agency_name = agency_name;
        this.is_study = is_study;
        this.img = img;
        this.course_name = course_name;
        this.sign = sign;
        this.price = price;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getIs_study() {
        return is_study;
    }

    public void setIs_study(String is_study) {
        this.is_study = is_study;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
