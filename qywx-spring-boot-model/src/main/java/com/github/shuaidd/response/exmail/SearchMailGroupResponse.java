package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

public class SearchMailGroupResponse extends AbstractBaseResponse {

    private Integer count;

    private List<GroupsDTO> groups;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<GroupsDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsDTO> groups) {
        this.groups = groups;
    }

    public static class GroupsDTO {

        @JsonProperty("groupid")
        private String groupId;

        @JsonProperty("groupname")
        private String groupName;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }
}
