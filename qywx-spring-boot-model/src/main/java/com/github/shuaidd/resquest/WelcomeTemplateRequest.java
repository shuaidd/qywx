package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.MiniProgram;
import com.github.shuaidd.dto.MsgImage;
import com.github.shuaidd.dto.MsgLink;
import com.github.shuaidd.dto.MsgText;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-06 22:26
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
}
