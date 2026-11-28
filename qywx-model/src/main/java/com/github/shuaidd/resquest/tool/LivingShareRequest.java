package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 15:07
 **/
public class LivingShareRequest {

    @JsonProperty("ww_share_code")
    private String wwShareCode;

    public String getWwShareCode() {
        return wwShareCode;
    }

    public void setWwShareCode(String wwShareCode) {
        this.wwShareCode = wwShareCode;
    }
}
