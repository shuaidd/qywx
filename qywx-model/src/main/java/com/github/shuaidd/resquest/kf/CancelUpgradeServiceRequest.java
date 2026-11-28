package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 14:34
 **/
public class CancelUpgradeServiceRequest {

    /**
     * 客服帐号ID
     */
    @JsonProperty("open_kfid")
    private String openKfId;

    /**
     * 微信客户的external_userid
     */
    @JsonProperty("external_userid")
    private String externalUserId;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", CancelUpgradeServiceRequest.class.getSimpleName() + "[", "]")
                .add("openKfId='" + openKfId + "'")
                .add("externalUserId='" + externalUserId + "'")
                .toString();
    }
}
