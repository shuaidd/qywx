package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 模版卡片水平内容
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardHorizontalContent {

    private Integer type;

    private String keyname;

    private String value;

    private String url;

    @JsonProperty("media_id")
    private String mediaId;

    private String userid;

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public String getKeyname() { return keyname; }
    public void setKeyname(String keyname) { this.keyname = keyname; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getMediaId() { return mediaId; }
    public void setMediaId(String mediaId) { this.mediaId = mediaId; }
    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }
}