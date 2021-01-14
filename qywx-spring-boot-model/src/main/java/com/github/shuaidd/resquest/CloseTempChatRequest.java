package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 15:55
 **/
public class CloseTempChatRequest {

    /**
     * 企业成员的userid
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 客户的外部联系人userid
     */
    @JsonProperty("external_userid")
    private String externalUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }
}
