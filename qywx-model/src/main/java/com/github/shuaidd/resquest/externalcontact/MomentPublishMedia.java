package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 企业朋友圈图片或视频素材
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishMedia {

    /**
     * 通过上传附件资源接口获得的素材 ID
     */
    @JsonProperty("media_id")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mediaId", mediaId)
                .toString();
    }
}