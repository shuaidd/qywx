package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:51
 **/
public class NewsArticle {

    private String title;
    private String description;
    private String url;
    @JsonProperty("picurl")
    private String picUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return new StringJoiner(", ", NewsArticle.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .add("url='" + url + "'")
                .add("picurl='" + picUrl + "'")
                .toString();
    }
}
