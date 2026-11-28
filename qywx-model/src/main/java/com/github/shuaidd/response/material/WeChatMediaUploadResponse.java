package com.github.shuaidd.response.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 描述 enjoy your life
 *
 * @author ddshuai
 * date 2018-11-28 17:05
 **/
public class WeChatMediaUploadResponse extends AbstractBaseResponse {

    private String type;

    @JsonProperty("media_id")
    private String mediaId;

    /** 上传时间：企业微信返回秒级时间戳字符串，必须按秒解析（Jackson 默认会把数字字符串当成毫秒） */
    @JsonProperty("created_at")
    @JsonDeserialize(using = Long2DateDeserializer.class)
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
