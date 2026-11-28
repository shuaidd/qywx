package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-11-25 11:21
 **/
public class SyncMsgRequest {

    private String cursor;

    private String token;

    private Integer limit;

    @JsonProperty("open_kfid")
    private String openKfId;

    @JsonProperty("voice_format")
    private Integer  voiceFormat;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }

    public Integer getVoiceFormat() {
        return voiceFormat;
    }

    public void setVoiceFormat(Integer voiceFormat) {
        this.voiceFormat = voiceFormat;
    }
}
