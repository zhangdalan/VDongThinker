package com.thinker.vdongthinker.bean;

/**
 * Created by zt on 2018/12/17.
 */

public class CommunityBean {
    private int video;//0是video1是photo

    public CommunityBean(int video) {
        this.video = video;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }
}
