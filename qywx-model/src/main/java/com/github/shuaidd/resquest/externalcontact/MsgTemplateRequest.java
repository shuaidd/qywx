package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.V2GroupMsg;
import com.github.shuaidd.dto.message.MiniProgram;
import com.github.shuaidd.dto.message.MsgImage;
import com.github.shuaidd.dto.message.MsgLink;
import com.github.shuaidd.dto.message.MsgText;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 21:34
 **/
public class MsgTemplateRequest {
    @JsonProperty("chat_type")
    private String chatType;
    @JsonProperty("external_userid")
    private List<String> externalUserId;
    private String sender;
    private MsgText text;
    private List<V2GroupMsg> attachments;

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public List<String> getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(List<String> externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
    }

    public List<V2GroupMsg> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<V2GroupMsg> attachments) {
        this.attachments = attachments;
    }
}
