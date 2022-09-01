package com.github.shuaidd.dto.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 22:46
 **/
public class SpaceInfo {

    @JsonProperty(value = "spaceid")
    private String spaceId;

    @JsonProperty(value = "space_name")
    private String spaceName;

    /**
     * 区分创建空间类型, 0:普通 1:相册
     */
    @JsonProperty(value = "space_sub_type")
    private Integer spaceSubType;

    @JsonProperty(value = "auth_list")
    private AuthList authList;

    public static class AuthList {
        @JsonProperty(value = "auth_info")
        private List<SpaceAuthItem> authInfo;

        /**
         * 空间无权限成员userid (成员在一个有权限的部门中, 自己退出空间或者被移除权限)
         */
        @JsonProperty("quit_userid")
        private List<String> quitUserId;

        public List<SpaceAuthItem> getAuthInfo() {
            return authInfo;
        }

        public void setAuthInfo(List<SpaceAuthItem> authInfo) {
            this.authInfo = authInfo;
        }

        public List<String> getQuitUserId() {
            return quitUserId;
        }

        public void setQuitUserId(List<String> quitUserId) {
            this.quitUserId = quitUserId;
        }
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

    public Integer getSpaceSubType() {
        return spaceSubType;
    }

    public void setSpaceSubType(Integer spaceSubType) {
        this.spaceSubType = spaceSubType;
    }

    public AuthList getAuthList() {
        return authList;
    }

    public void setAuthList(AuthList authList) {
        this.authList = authList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SpaceInfo.class.getSimpleName() + "[", "]")
                .add("spaceId='" + spaceId + "'")
                .add("spaceName='" + spaceName + "'")
                .add("spaceSubType=" + spaceSubType)
                .add("authList=" + authList)
                .toString();
    }
}
