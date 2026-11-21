package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 停止企业群发请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GroupMsgCancelRequest {

    /**
     * 要停止继续发送的企业群消息 ID；来自群发记录列表接口返回的 msgid
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