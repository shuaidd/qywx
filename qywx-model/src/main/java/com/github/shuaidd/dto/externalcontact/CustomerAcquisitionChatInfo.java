package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 成员多次收消息详情
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionChatInfo {

    /**
     * 成员收到的此客户的消息次数
     */
    @JsonProperty("recv_msg_cnt")
    private Integer recvMsgCnt;

    /**
     * 成员添加客户时使用的获客链接 ID
     */
    @JsonProperty("link_id")
    private String linkId;

    /**
     * 成员添加客户时携带的 state 值，可用于识别获客渠道
     */
    @JsonProperty("state")
    private String state;

    public Integer getRecvMsgCnt() {
        return recvMsgCnt;
    }

    public void setRecvMsgCnt(Integer recvMsgCnt) {
        this.recvMsgCnt = recvMsgCnt;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
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
                .append("recvMsgCnt", recvMsgCnt)
                .append("linkId", linkId)
                .append("state", state)
                .toString();
    }
}
