package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-02 12:51
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonSpaceRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("spaceid")
    private String spaceId;

    @JsonProperty("fatherid")
    private String fatherId;

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }
}
