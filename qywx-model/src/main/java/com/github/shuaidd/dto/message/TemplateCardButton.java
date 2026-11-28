package com.github.shuaidd.dto.message;

/**
 * 模版卡片按钮
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardButton {

    private Integer type;

    private String text;

    private Integer style;

    private String key;

    private String url;

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Integer getStyle() { return style; }
    public void setStyle(Integer style) { this.style = style; }
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}