package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 22:21
 **/
public class TransferResultRequest {
    /**
     * 外部联系人的userid，注意不是企业成员的帐号
     */
    @JsonProperty("external_userid")
    private String externalUserId;

    /**
     * 原跟进成员的userid
     */
    @JsonProperty("handover_userid")
    private String handoverUserId;

    /**
     * 接替成员的userid
     */
    @JsonProperty("takeover_userid")
    private String takeoverUserId;

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getHandoverUserId() {
        return handoverUserId;
    }

    public void setHandoverUserId(String handoverUserId) {
        this.handoverUserId = handoverUserId;
    }

    public String getTakeoverUserId() {
        return takeoverUserId;
    }

    public void setTakeoverUserId(String takeoverUserId) {
        this.takeoverUserId = takeoverUserId;
    }
}
