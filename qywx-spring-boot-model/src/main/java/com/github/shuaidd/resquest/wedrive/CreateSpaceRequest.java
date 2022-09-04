package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.wedrive.AuthItem;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-01 22:04
 **/
public class CreateSpaceRequest {

    @JsonProperty(value = "userid")
    private String userId;

    @JsonProperty(value = "space_name")
    private String spaceName;

    /**
     * 区分创建空间类型, 0:普通 1:相册
     */
    @JsonProperty(value = "space_sub_type")
    private Integer spaceSubType;

    @JsonProperty(value = "auth_info")
    private List<AuthItem> authInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public Integer getSpaceSubType() {
        return spaceSubType;
    }

    public void setSpaceSubType(Integer spaceSubType) {
        this.spaceSubType = spaceSubType;
    }

    public List<AuthItem> getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(List<AuthItem> authInfo) {
        this.authInfo = authInfo;
    }
}
