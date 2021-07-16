package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 22:22
 **/
public class CancelLivingRequest {
    @JsonProperty("livingid")
    private String livingId;

    public String getLivingId() {
        return livingId;
    }

    public void setLivingId(String livingId) {
        this.livingId = livingId;
    }
}
