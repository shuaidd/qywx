package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.message.MiniProgram;
import com.github.shuaidd.dto.message.MsgImage;
import com.github.shuaidd.dto.message.MsgLink;
import com.github.shuaidd.dto.message.MsgText;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 22:26
 **/
public class WelcomeTemplateRequest {

    @JsonProperty("agentid")
    private Integer agentId;

    private MsgText text;

    private MsgLink link;

    private MsgImage image;

    private MiniProgram miniprogram;

    @JsonProperty("template_id")
    private String templateId;

    /**
     * 是否通知成员将这条入群欢迎语应用到客户群中，0-不通知，1-通知， 不填则通知
     */
    private Integer notify;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
    }

    public MsgLink getLink() {
        return link;
    }

    public void setLink(MsgLink link) {
        this.link = link;
    }

    public MsgImage getImage() {
        return image;
    }

    public void setImage(MsgImage image) {
        this.image = image;
    }

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getNotify() {
        return notify;
    }

    public void setNotify(Integer notify) {
        this.notify = notify;
    }
}
