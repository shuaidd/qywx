package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 企业朋友圈图文链接附件
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishLink {

    /**
     * 图文消息标题；最长 64 个字符
     */
    private String title;

    /**
     * 图文消息跳转链接
     */
    private String url;

    /**
     * 图文链接封面图片素材 ID
     */
    @JsonProperty("media_id")
    private String mediaId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("url", url)
                .append("mediaId", mediaId)
                .toString();
    }
}