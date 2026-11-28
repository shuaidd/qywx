package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 外部联系人openid转换结果响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ExternalUserOpenIdResponse extends AbstractBaseResponse {

    /**
     * 该企业的外部联系人openid
     */
    @JsonProperty("openid")
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
