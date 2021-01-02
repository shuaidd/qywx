package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 15:24
 **/
public class MiniProgram {
    @JsonProperty("appid")
    private String appId;
    private String page;
    private String title;
    @JsonProperty("pic_media_id")
    private String picMediaId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicMediaId() {
        return picMediaId;
    }

    public void setPicMediaId(String picMediaId) {
        this.picMediaId = picMediaId;
    }
}
