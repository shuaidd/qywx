package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-01 22:34
 **/
public class RenameSpaceRequest {

    @JsonProperty(value = "userid")
    private String userId;

    @JsonProperty(value = "spaceid")
    private String spaceId;

    @JsonProperty(value = "space_name")
    private String spaceName;

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

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }
}
