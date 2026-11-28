package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.CursorPageRequest;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 22:16
 **/
public class GroupMsgSendRequest extends CursorPageRequest {
    @JsonProperty("userid")
    private String userId;
    @JsonProperty("msgid")
    private String msgId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
