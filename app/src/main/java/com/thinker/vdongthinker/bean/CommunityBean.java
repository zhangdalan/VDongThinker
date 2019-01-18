package com.thinker.vdongthinker.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 */

public class CommunityBean implements Serializable {
    private String touxiang;
    private String name;
    private String type;
    private String content;
    private int isVideo;//0是图片1是vedio
    private String vedio;
    private List<String> imgs;
    private String date;
    private int assess_num;
    private int like;
//    private List<CourseAssessBean> list_assess;
//    private String vedio_img_url;
//    private int IS_ZAN;//赞状态，0无1有
//    private int IS_GUANZHU;//关注，0无1有


    public CommunityBean(String vedio) {
        this.vedio = vedio;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(int isVideo) {
        this.isVideo = isVideo;
    }

    public String getVedio() {
        return vedio;
    }

    public void setVedio(String vedio) {
        this.vedio = vedio;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAssess_num() {
        return assess_num;
    }

    public void setAssess_num(int assess_num) {
        this.assess_num = assess_num;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
