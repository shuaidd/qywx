package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 企业朋友圈发表任务详细结果
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishTaskResult extends AbstractBaseResponse {

    /**
     * 创建成功后的朋友圈 ID
     */
    @JsonProperty("moment_id")
    private String momentId;

    /**
     * 不合法的任务执行者列表
     */
    @JsonProperty("invalid_sender_list")
    private MomentPublishInvalidSenderList invalidSenderList;

    /**
     * 不合法的目标客户标签列表
     */
    @JsonProperty("invalid_external_contact_list")
    private MomentPublishInvalidExternalContactList invalidExternalContactList;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public MomentPublishInvalidSenderList getInvalidSenderList() {
        return invalidSenderList;
    }

    public void setInvalidSenderList(MomentPublishInvalidSenderList invalidSenderList) {
        this.invalidSenderList = invalidSenderList;
    }

    public MomentPublishInvalidExternalContactList getInvalidExternalContactList() {
        return invalidExternalContactList;
    }

    public void setInvalidExternalContactList(MomentPublishInvalidExternalContactList invalidExternalContactList) {
        this.invalidExternalContactList = invalidExternalContactList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("momentId", momentId)
                .append("invalidSenderList", invalidSenderList)
                .append("invalidExternalContactList", invalidExternalContactList)
                .toString();
    }
}