package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 模版卡片选项
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardOption {

    private String id;

    private String text;

    @JsonProperty("is_checked")
    private Boolean checked;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Boolean getChecked() { return checked; }
    public void setChecked(Boolean checked) { this.checked = checked; }
}