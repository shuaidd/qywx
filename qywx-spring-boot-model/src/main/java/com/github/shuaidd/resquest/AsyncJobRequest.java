package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.CallbackData;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-06 20:27
 **/
public class AsyncJobRequest {

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("to_invite")
    private Boolean toInvite;

    private CallbackData callback;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Boolean getToInvite() {
        return toInvite;
    }

    public void setToInvite(Boolean toInvite) {
        this.toInvite = toInvite;
    }

    public CallbackData getCallback() {
        return callback;
    }

    public void setCallback(CallbackData callback) {
        this.callback = callback;
    }
}
