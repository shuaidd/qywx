package com.github.shuaidd.resquest.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-05 21:49
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderTokenRequest {

    @JsonProperty("corpid")
    private String corpId;

    @JsonProperty("provider_secret")
    private String providerSecret;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getProviderSecret() {
        return providerSecret;
    }

    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }
}
