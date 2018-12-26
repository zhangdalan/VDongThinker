package com.thinker.vdongthinker.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 */

public class CommunityBean implements Serializable {
    private int video;//0是video1是photo
    private String name;
    private String type;
    private String touxiang_url;
    private String content;
    private List<String> list_img;
    private List<CourseAssessBean> list_assess;
    private String vedio_url;
    private String vedio_img_url;
    private int IS_ZAN;//赞状态，0无1有
    private int IS_GUANZHU;//关注，0无1有

    public CommunityBean(int video) {
        this.video = video;
    }

    public CommunityBean(int video, String name, String type, String touxiang_url, String content, List<String> list_img, List<CourseAssessBean> list_assess, String vedio_url, String vedio_img_url, int IS_ZAN, int IS_GUANZHU) {
        this.video = video;
        this.name = name;
        this.type = type;
        this.touxiang_url = touxiang_url;
        this.content = content;
        this.list_img = list_img;
        this.list_assess = list_assess;
        this.vedio_url = vedio_url;
        this.vedio_img_url = vedio_img_url;
        this.IS_ZAN = IS_ZAN;
        this.IS_GUANZHU = IS_GUANZHU;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTouxiang_url() {
        return touxiang_url;
    }

    public void setTouxiang_url(String touxiang_url) {
        this.touxiang_url = touxiang_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getList_img() {
        return list_img;
    }

    public void setList_img(List<String> list_img) {
        this.list_img = list_img;
    }

    public List<CourseAssessBean> getList_assess() {
        return list_assess;
    }

    public void setList_assess(List<CourseAssessBean> list_assess) {
        this.list_assess = list_assess;
    }

    public String getVedio_url() {
        return vedio_url;
    }

    public void setVedio_url(String vedio_url) {
        this.vedio_url = vedio_url;
    }

    public String getVedio_img_url() {
        return vedio_img_url;
    }

    public void setVedio_img_url(String vedio_img_url) {
        this.vedio_img_url = vedio_img_url;
    }

    public int getIS_ZAN() {
        return IS_ZAN;
    }

    public void setIS_ZAN(int IS_ZAN) {
        this.IS_ZAN = IS_ZAN;
    }

    public int getIS_GUANZHU() {
        return IS_GUANZHU;
    }

    public void setIS_GUANZHU(int IS_GUANZHU) {
        this.IS_GUANZHU = IS_GUANZHU;
    }
}
