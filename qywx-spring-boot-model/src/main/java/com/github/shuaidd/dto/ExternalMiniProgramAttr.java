package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 19:36
 **/
public class ExternalMiniProgramAttr {
    private String title;
    @JsonProperty("pagepath")
    private String pagePath;
    @JsonProperty("appid")
    private String appId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("pagePath", pagePath)
                .append("appId", appId)
                .toString();
    }
}
