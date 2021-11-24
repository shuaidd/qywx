package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 10:08
 **/
public class OpenPartyId {

    @JsonProperty("open_partyid")
    private String openPartyId;

    public String getOpenPartyId() {
        return openPartyId;
    }

    public void setOpenPartyId(String openPartyId) {
        this.openPartyId = openPartyId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OpenPartyId.class.getSimpleName() + "[", "]")
                .add("openPartyId=" + openPartyId)
                .toString();
    }
}
