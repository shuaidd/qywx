package com.github.shuaidd.dto.template;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-15 15:36
 **/
public class TemplateText {
    private String text;
    private String lang;

    public TemplateText() {
    }

    public TemplateText(String text, String lang) {
        this.text = text;
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TemplateText.class.getSimpleName() + "[", "]")
                .add("text='" + text + "'")
                .add("lang='" + lang + "'")
                .toString();
    }
}
