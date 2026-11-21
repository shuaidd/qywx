package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 模版卡片左图右文区域
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardImageTextArea {

    private Integer type;

    private String url;

    private String appid;

    private String pagepath;

    private String title;

    private String desc;

    @JsonProperty("image_url")
    private String imageUrl;

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getAppid() { return appid; }
    public void setAppid(String appid) { this.appid = appid; }
    public String getPagepath() { return pagepath; }
    public void setPagepath(String pagepath) { this.pagepath = pagepath; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}