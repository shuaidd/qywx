package com.github.shuaidd.resquest.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.message.TemplateCard;

import java.util.List;

/**
 * 更新模版卡片消息请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UpdateTemplateCardRequest {

    @JsonProperty("userids")
    private List<String> userIds;

    @JsonProperty("partyids")
    private List<Integer> partyIds;

    @JsonProperty("tagids")
    private List<Integer> tagIds;

    private Integer atall;

    private Integer agentid;

    @JsonProperty("response_code")
    private String responseCode;

    @JsonProperty("enable_id_trans")
    private Integer enableIdTrans;

    private UpdateTemplateCardButton button;

    @JsonProperty("template_card")
    private TemplateCard templateCard;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getPartyIds() {
        return partyIds;
    }

    public void setPartyIds(List<Integer> partyIds) {
        this.partyIds = partyIds;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getAtall() {
        return atall;
    }

    public void setAtall(Integer atall) {
        this.atall = atall;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getEnableIdTrans() {
        return enableIdTrans;
    }

    public void setEnableIdTrans(Integer enableIdTrans) {
        this.enableIdTrans = enableIdTrans;
    }

    public UpdateTemplateCardButton getButton() {
        return button;
    }

    public void setButton(UpdateTemplateCardButton button) {
        this.button = button;
    }

    public TemplateCard getTemplateCard() {
        return templateCard;
    }

    public void setTemplateCard(TemplateCard templateCard) {
        this.templateCard = templateCard;
    }
}