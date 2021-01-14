package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 17:48
 **/
public class ExternalUser {

    @JsonProperty("external_userid")
    private String externalUserId;

    private Integer status;

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
