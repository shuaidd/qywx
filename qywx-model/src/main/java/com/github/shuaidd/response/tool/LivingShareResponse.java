package com.github.shuaidd.response.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 15:04
 **/
public class LivingShareResponse extends AbstractBaseResponse {

    @JsonProperty("livingid")
    private String livingId;

    @JsonProperty("viewer_userid")
    private String viewerUserId;

    @JsonProperty("viewer_external_userid")
    private String viewerExternalUserId;

    @JsonProperty("invitor_userid")
    private String invitorUserId;

    @JsonProperty("invitor_external_userid")
    private String invitorExternalUserId;

    public String getLivingId() {
        return livingId;
    }

    public void setLivingId(String livingId) {
        this.livingId = livingId;
    }

    public String getViewerUserId() {
        return viewerUserId;
    }

    public void setViewerUserId(String viewerUserId) {
        this.viewerUserId = viewerUserId;
    }

    public String getViewerExternalUserId() {
        return viewerExternalUserId;
    }

    public void setViewerExternalUserId(String viewerExternalUserId) {
        this.viewerExternalUserId = viewerExternalUserId;
    }

    public String getInvitorUserId() {
        return invitorUserId;
    }

    public void setInvitorUserId(String invitorUserId) {
        this.invitorUserId = invitorUserId;
    }

    public String getInvitorExternalUserId() {
        return invitorExternalUserId;
    }

    public void setInvitorExternalUserId(String invitorExternalUserId) {
        this.invitorExternalUserId = invitorExternalUserId;
    }
}
