package com.github.shuaidd.resquest.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.message.MsgMarkdown;
import com.github.shuaidd.dto.message.TemplateCard;
import com.github.shuaidd.enums.MsgType;

import java.util.Map;

/**
 * 智能机器人主动回复消息请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class AiBotResponseRequest {

    @JsonProperty("msgtype")
    private MsgType msgType;

    private MsgMarkdown markdown;

    @JsonProperty("template_card")
    private TemplateCard templateCard;

    @JsonProperty("to_ai_assistant")
    private Map<String, Object> toAiAssistant;

    public MsgType getMsgType() { return msgType; }
    public void setMsgType(MsgType msgType) { this.msgType = msgType; }
    public MsgMarkdown getMarkdown() { return markdown; }
    public void setMarkdown(MsgMarkdown markdown) { this.markdown = markdown; }
    public TemplateCard getTemplateCard() { return templateCard; }
    public void setTemplateCard(TemplateCard templateCard) { this.templateCard = templateCard; }
    public Map<String, Object> getToAiAssistant() { return toAiAssistant; }
    public void setToAiAssistant(Map<String, Object> toAiAssistant) { this.toAiAssistant = toAiAssistant; }
}