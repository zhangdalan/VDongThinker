package com.thinker.vdongthinker.bean;

/**
 * Created by zjw on 2018/12/26.
 */
public class MyAgencyBean {
    private String agency_name;
    private String address;
    private String course_num;
    private String people_num;

    public MyAgencyBean() {
    }

    public MyAgencyBean(String agency_name, String address, String course_num, String people_num) {
        this.agency_name = agency_name;
        this.address = address;
        this.course_num = course_num;
        this.people_num = people_num;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public String getPeople_num() {
        return people_num;
    }

    public void setPeople_num(String people_num) {
        this.people_num = people_num;
    }
}
