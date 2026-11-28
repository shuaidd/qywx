package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 10:07
 **/
public class Applyer {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("partyid")
    private String partyId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
}
