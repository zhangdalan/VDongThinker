package com.thinker.vdongthinker.bean;

/**
 * Created by zjw on 2018/12/28.
 */
public class MessageReplayBean {
    private String name;
    private String time;
    private String content;
    private String sign;

    public MessageReplayBean() {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

}
