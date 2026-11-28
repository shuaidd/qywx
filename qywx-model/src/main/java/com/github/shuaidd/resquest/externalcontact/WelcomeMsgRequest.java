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
 * date 2021-01-06 22:23
 **/
public class WelcomeMsgRequest {

    @JsonProperty("welcome_code")
    private String welcomeCode;

    private MsgText text;

    private List<V2GroupMsg> attachments;

    public String getWelcomeCode() {
        return welcomeCode;
    }

    public void setWelcomeCode(String welcomeCode) {
        this.welcomeCode = welcomeCode;
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
