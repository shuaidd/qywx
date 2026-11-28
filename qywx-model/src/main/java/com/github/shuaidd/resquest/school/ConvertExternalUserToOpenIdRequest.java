package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信外部联系人 userid 转openid的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ConvertExternalUserToOpenIdRequest {

    /**
     * 外部联系人的userid，注意不是企业成员的账号
     */
    @JsonProperty("external_userid")
    private String externalUserId;

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }
}
