package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionChatInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取成员多次收消息详情响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionChatInfoResponse extends AbstractBaseResponse {

    /**
     * 接收消息的企业成员 userid
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 发送消息的外部联系人（客户）ID
     */
    @JsonProperty("external_userid")
    private String externalUserid;

    /**
     * 该成员与该客户的多次收消息详情
     */
    @JsonProperty("chat_info")
    private CustomerAcquisitionChatInfo chatInfo;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getExternalUserid() {
        return externalUserid;
    }

    public void setExternalUserid(String externalUserid) {
        this.externalUserid = externalUserid;
    }

    public CustomerAcquisitionChatInfo getChatInfo() {
        return chatInfo;
    }

    public void setChatInfo(CustomerAcquisitionChatInfo chatInfo) {
        this.chatInfo = chatInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("userid", userid)
                .append("externalUserid", externalUserid)
                .append("chatInfo", chatInfo)
                .toString();
    }
}
