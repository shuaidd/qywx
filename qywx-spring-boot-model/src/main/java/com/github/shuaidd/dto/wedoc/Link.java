package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 */
public class Link {

    /**
     * 链接url
     */
    @JsonProperty("url")
    private String url;
    /**
     * 链接标题
     */
    @JsonProperty("text")
    private String text;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
