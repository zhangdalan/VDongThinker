package com.thinker.vdongthinker.bean;

/**
 * Created by zjw on 2018/12/28.
 */
public class MessageZanBean {
    private String name;
    private String time;
    private String content;
    private int IS_VEDIO;

    public MessageZanBean(int IS_VEDIO) {
        this.IS_VEDIO = IS_VEDIO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIS_VEDIO() {
        return IS_VEDIO;
    }

    public void setIS_VEDIO(int IS_VEDIO) {
        this.IS_VEDIO = IS_VEDIO;
    }
}
