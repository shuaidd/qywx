package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 模版卡片引用区域
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardQuoteArea {

    private Integer type;

    private String url;

    private String appid;

    private String pagepath;

    private String title;

    @JsonProperty("quote_text")
    private String quoteText;

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
    public String getQuoteText() { return quoteText; }
    public void setQuoteText(String quoteText) { this.quoteText = quoteText; }
}