package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 模版卡片图片
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardImage {

    private String url;

    @JsonProperty("aspect_ratio")
    private Double aspectRatio;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Double getAspectRatio() { return aspectRatio; }
    public void setAspectRatio(Double aspectRatio) { this.aspectRatio = aspectRatio; }
}