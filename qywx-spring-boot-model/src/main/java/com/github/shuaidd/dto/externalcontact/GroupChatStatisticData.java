package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-07 16:12
 **/
public class GroupChatStatisticData {

    @JsonProperty("new_chat_cnt")
    private Integer newChatCnt;

    @JsonProperty("chat_total")
    private Integer chatTotal;

    @JsonProperty("chat_has_msg")
    private Integer chatHasMsg;

    @JsonProperty("new_member_cnt")
    private Integer newMemberCnt;

    @JsonProperty("member_total")
    private Integer memberTotal;

    @JsonProperty("member_has_msg")
    private Integer memberHasMsg;

    @JsonProperty("msg_total")
    private Integer msgTotal;

    public Integer getNewChatCnt() {
        return newChatCnt;
    }

    public void setNewChatCnt(Integer newChatCnt) {
        this.newChatCnt = newChatCnt;
    }

    public Integer getChatTotal() {
        return chatTotal;
    }

    public void setChatTotal(Integer chatTotal) {
        this.chatTotal = chatTotal;
    }

    public Integer getChatHasMsg() {
        return chatHasMsg;
    }

    public void setChatHasMsg(Integer chatHasMsg) {
        this.chatHasMsg = chatHasMsg;
    }

    public Integer getNewMemberCnt() {
        return newMemberCnt;
    }

    public void setNewMemberCnt(Integer newMemberCnt) {
        this.newMemberCnt = newMemberCnt;
    }

    public Integer getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(Integer memberTotal) {
        this.memberTotal = memberTotal;
    }

    public Integer getMemberHasMsg() {
        return memberHasMsg;
    }

    public void setMemberHasMsg(Integer memberHasMsg) {
        this.memberHasMsg = memberHasMsg;
    }

    public Integer getMsgTotal() {
        return msgTotal;
    }

    public void setMsgTotal(Integer msgTotal) {
        this.msgTotal = msgTotal;
    }
}
