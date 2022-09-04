package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 15:21
 **/
public class MsgLink {

    private String title;
    private String desc;
    private String url;
    @JsonProperty("picurl")
    private String picUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MsgLink.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("desc='" + desc + "'")
                .add("url='" + url + "'")
                .add("picUrl='" + picUrl + "'")
                .toString();
    }
}
