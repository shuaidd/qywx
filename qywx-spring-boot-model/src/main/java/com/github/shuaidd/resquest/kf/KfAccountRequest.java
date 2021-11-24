package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-24 16:17
 **/
public class KfAccountRequest {

    private String name;

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("open_kfid")
    private String openKfId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KfAccountRequest.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("mediaId='" + mediaId + "'")
                .toString();
    }
}
