package com.github.shuaidd.response.wedoc;

import com.github.shuaidd.response.AbstractBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 发布智能文档响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SmartDocPublishResponse extends AbstractBaseResponse {

    /**
     * 发布页分享码
     */
    @JsonProperty("share_code")
    private String shareCode;

    /**
     * 发布页访问链接
     */
    @JsonProperty("publish_url")
    private String publishUrl;

    /**
     * 发布版本号
     */
    @JsonProperty("version")
    private Long version;

    /**
     * 发布时间戳
     */
    @JsonProperty("publish_time")
    private Long publishTime;

    /**
     * 发布后的文档标题
     */
    @JsonProperty("publish_doc_title")
    private String publishDocTitle;

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getPublishUrl() {
        return publishUrl;
    }

    public void setPublishUrl(String publishUrl) {
        this.publishUrl = publishUrl;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishDocTitle() {
        return publishDocTitle;
    }

    public void setPublishDocTitle(String publishDocTitle) {
        this.publishDocTitle = publishDocTitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("shareCode", shareCode)
                .append("publishUrl", publishUrl)
                .append("version", version)
                .append("publishTime", publishTime)
                .append("publishDocTitle", publishDocTitle)
                .toString();
    }
}