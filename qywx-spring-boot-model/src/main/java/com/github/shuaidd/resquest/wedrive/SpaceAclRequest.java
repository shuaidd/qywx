package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedrive.SpaceAuthItem;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-02 12:33
 **/
public class SpaceAclRequest {

    @JsonProperty(value = "userid")
    private String userId;

    @JsonProperty(value = "spaceid")
    private String spaceId;

    @JsonProperty(value = "auth_info")
    private List<SpaceAuthItem> authInfo;

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

    public List<SpaceAuthItem> getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(List<SpaceAuthItem> authInfo) {
        this.authInfo = authInfo;
    }
}
