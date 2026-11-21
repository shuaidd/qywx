package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 通过获客链接添加的客户信息
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionCustomer {

    /**
     * 客户 external_userid
     */
    @JsonProperty("external_userid")
    private String externalUserid;

    /**
     * 通过获客链接添加此客户的跟进人 userid
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 会话状态；0-客户未发消息，1-客户已发送消息，2-客户发送消息状态未知
     */
    @JsonProperty("chat_status")
    private Integer chatStatus;

    /**
     * 获客链接渠道标识；链接可拼接 customer_channel 自定义参数，最长 64 字节，超长会被截断
     */
    @JsonProperty("state")
    private String state;

    public String getExternalUserid() {
        return externalUserid;
    }

    public void setExternalUserid(String externalUserid) {
        this.externalUserid = externalUserid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(Integer chatStatus) {
        this.chatStatus = chatStatus;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("externalUserid", externalUserid)
                .append("userid", userid)
                .append("chatStatus", chatStatus)
                .append("state", state)
                .toString();
    }
}