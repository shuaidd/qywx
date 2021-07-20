package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 21:41
 **/
public class MsgTemplateResponse extends AbstractBaseResponse {
    @JsonProperty("fail_list")
    private List<String> failList;
    @JsonProperty("msgid")
    private String msgId;

    public List<String> getFailList() {
        return failList;
    }

    public void setFailList(List<String> failList) {
        this.failList = failList;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("failList", failList)
                .append("msgId", msgId)
                .toString();
    }
}
