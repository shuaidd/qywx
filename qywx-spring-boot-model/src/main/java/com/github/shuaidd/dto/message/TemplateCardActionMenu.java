package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 模版卡片右上角更多操作菜单
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardActionMenu {

    private String desc;

    @JsonProperty("action_list")
    private List<TemplateCardAction> actionList;

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public List<TemplateCardAction> getActionList() { return actionList; }
    public void setActionList(List<TemplateCardAction> actionList) { this.actionList = actionList; }
}