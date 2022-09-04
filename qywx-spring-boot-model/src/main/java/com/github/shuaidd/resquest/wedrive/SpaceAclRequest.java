package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedrive.AuthItem;

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
    private List<AuthItem> authInfo;

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

    public List<AuthItem> getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(List<AuthItem> authInfo) {
        this.authInfo = authInfo;
    }
}
