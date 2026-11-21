package com.github.shuaidd.dto.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * tmp_external_userid 转换结果
 *
 * @author ddshuai
 * date 2026-11-21
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmpExternalUserIdResult {

    @JsonProperty("tmp_external_userid")
    private String tmpExternalUserId;

    @JsonProperty("external_userid")
    private String externalUserId;

    @JsonProperty("corpid")
    private String corpId;

    @JsonProperty("userid")
    private String userId;

    public String getTmpExternalUserId() {
        return tmpExternalUserId;
    }

    public void setTmpExternalUserId(String tmpExternalUserId) {
        this.tmpExternalUserId = tmpExternalUserId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
