package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:19
 **/
public class MsgFile {

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
        return new StringJoiner(", ", MsgFile.class.getSimpleName() + "[", "]")
                .add("mediaId='" + mediaId + "'")
                .toString();
    }
}
