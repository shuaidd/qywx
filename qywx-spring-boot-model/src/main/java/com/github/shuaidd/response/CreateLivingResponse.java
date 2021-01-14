package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 22:16
 **/
public class CreateLivingResponse extends AbstractBaseResponse {

    @JsonProperty("livingid")
    private String livingId;

    public String getLivingId() {
        return livingId;
    }

    public void setLivingId(String livingId) {
        this.livingId = livingId;
    }
}
