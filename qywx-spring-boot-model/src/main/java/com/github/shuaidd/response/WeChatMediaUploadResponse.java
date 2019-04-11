package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 描述 enjoy your life
 *
 * author ddshuai
 * date 2018-11-28 17:05
 **/
public class WeChatMediaUploadResponse extends AbstractBaseResponse {

    private String type;

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("created_at")
    private Date createdAt;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WeChatMediaUploadResponse.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("mediaId='" + mediaId + "'")
                .add("createdAt=" + createdAt)
                .toString();
    }
}
