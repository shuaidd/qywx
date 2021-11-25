package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-25 11:31
 **/
public class SendMsgResponse extends AbstractBaseResponse {

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
        return new StringJoiner(", ", SendMsgResponse.class.getSimpleName() + "[", "]")
                .add("msgId='" + msgId + "'")
                .toString();
    }
}
