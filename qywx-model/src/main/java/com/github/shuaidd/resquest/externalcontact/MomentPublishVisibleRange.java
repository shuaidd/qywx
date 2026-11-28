package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 企业朋友圈发表范围
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishVisibleRange {

    /**
     * 发表任务的执行者列表
     */
    @JsonProperty("sender_list")
    private MomentPublishSenderList senderList;

    /**
     * 可见到该朋友圈的客户标签列表
     */
    @JsonProperty("external_contact_list")
    private MomentPublishExternalContactList externalContactList;

    public MomentPublishSenderList getSenderList() {
        return senderList;
    }

    public void setSenderList(MomentPublishSenderList senderList) {
        this.senderList = senderList;
    }

    public MomentPublishExternalContactList getExternalContactList() {
        return externalContactList;
    }

    public void setExternalContactList(MomentPublishExternalContactList externalContactList) {
        this.externalContactList = externalContactList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("senderList", senderList)
                .append("externalContactList", externalContactList)
                .toString();
    }
}