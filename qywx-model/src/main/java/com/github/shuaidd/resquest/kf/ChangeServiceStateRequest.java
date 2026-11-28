package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 * @author ddshuai
 * date 2021-11-25 09:49
 **/
public class ChangeServiceStateRequest {

    @JsonProperty("open_kfid")
    private String openKfId;

    @JsonProperty("external_userid")
    private String externalUserId;

    @JsonProperty("service_state")
    private Integer serviceState;

    @JsonProperty("servicer_userid")
    private String servicerUserId;

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public Integer getServiceState() {
        return serviceState;
    }

    public void setServiceState(Integer serviceState) {
        this.serviceState = serviceState;
    }

    public String getServicerUserId() {
        return servicerUserId;
    }

    public void setServicerUserId(String servicerUserId) {
        this.servicerUserId = servicerUserId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChangeServiceStateRequest.class.getSimpleName() + "[", "]")
                .add("openKfId='" + openKfId + "'")
                .add("externalUserId='" + externalUserId + "'")
                .add("serviceState=" + serviceState)
                .add("servicerUserId='" + servicerUserId + "'")
                .toString();
    }
}
