package com.github.shuaidd.dto.message;

/**
 * 模版卡片跳转项
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardJump {

    private Integer type;

    private String title;

    private String url;

    private String appid;

    private String pagepath;

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getAppid() { return appid; }
    public void setAppid(String appid) { this.appid = appid; }
    public String getPagepath() { return pagepath; }
    public void setPagepath(String pagepath) { this.pagepath = pagepath; }
}