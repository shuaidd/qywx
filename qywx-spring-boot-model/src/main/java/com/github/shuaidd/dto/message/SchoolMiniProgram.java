package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 学校通知小程序消息
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class SchoolMiniProgram {

    private String appid;

    private String title;

    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    private String pagepath;

    public String getAppid() { return appid; }
    public void setAppid(String appid) { this.appid = appid; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getThumbMediaId() { return thumbMediaId; }
    public void setThumbMediaId(String thumbMediaId) { this.thumbMediaId = thumbMediaId; }
    public String getPagepath() { return pagepath; }
    public void setPagepath(String pagepath) { this.pagepath = pagepath; }
}