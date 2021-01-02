package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 09:52
 **/
public class FollowUserResponse extends AbstractBaseResponse {

    @JsonProperty("follow_user")
    private List<String> followUsers;

    public List<String> getFollowUsers() {
        return followUsers;
    }

    public void setFollowUsers(List<String> followUsers) {
        this.followUsers = followUsers;
    }
}
