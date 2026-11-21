package com.github.shuaidd.dto.message;

/**
 * 模版卡片整体点击跳转事件
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardActionLink {

    private Integer type;

    private String url;

    private String appid;

    private String pagepath;

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getAppid() { return appid; }
    public void setAppid(String appid) { this.appid = appid; }
    public String getPagepath() { return pagepath; }
    public void setPagepath(String pagepath) { this.pagepath = pagepath; }
}