package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 模版卡片来源样式
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardSource {

    @JsonProperty("icon_url")
    private String iconUrl;

    private String desc;

    @JsonProperty("desc_color")
    private Integer descColor;

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public Integer getDescColor() { return descColor; }
    public void setDescColor(Integer descColor) { this.descColor = descColor; }
}