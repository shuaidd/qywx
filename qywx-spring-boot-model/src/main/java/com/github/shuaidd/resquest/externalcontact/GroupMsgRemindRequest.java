package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 提醒成员群发请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GroupMsgRemindRequest {

    /**
     * 需要提醒成员发送的群消息 ID；来自群发记录相关接口返回的 msgid
     */
    @JsonProperty("msgid")
    private String msgId;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("msgId", msgId)
                .toString();
    }
}