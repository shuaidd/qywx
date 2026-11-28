package com.github.shuaidd.resquest.message;

/**
 * 模版卡片按钮更新请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UpdateTemplateCardButton {

    @com.fasterxml.jackson.annotation.JsonProperty("replace_name")
    private String replaceName;

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }
}