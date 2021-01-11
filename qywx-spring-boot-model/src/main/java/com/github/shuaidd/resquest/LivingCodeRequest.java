package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-11 22:28
 **/
public class LivingCodeRequest {
    @JsonProperty("livingid")
    private String livingId;
    @JsonProperty("openid")
    private String openId;

    public String getLivingId() {
        return livingId;
    }

    public void setLivingId(String livingId) {
        this.livingId = livingId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
