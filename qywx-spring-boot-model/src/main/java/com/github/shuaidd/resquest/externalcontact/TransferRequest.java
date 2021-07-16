package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 22:08
 **/
public class TransferRequest {

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

    /**
     * 转移成功后发给客户的消息，最多200个字符，不填则使用默认文案，目前只对在职成员分配客户的情况生效
     */
    @JsonProperty("transfer_success_msg")
    private String transferSuccessMsg;

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

    public String getTransferSuccessMsg() {
        return transferSuccessMsg;
    }

    public void setTransferSuccessMsg(String transferSuccessMsg) {
        this.transferSuccessMsg = transferSuccessMsg;
    }
}
