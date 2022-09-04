package com.github.shuaidd.response.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.tool.UserIdList;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-04 21:10
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenUserId2UserIdResponse {

    @JsonProperty("userid_list")
    private List<UserIdList> userIdList;

    @JsonProperty("invalid_open_userid_list")
    private List<String> invalidOpenUserIdList;

    public List<UserIdList> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<UserIdList> userIdList) {
        this.userIdList = userIdList;
    }

    public List<String> getInvalidOpenUserIdList() {
        return invalidOpenUserIdList;
    }

    public void setInvalidOpenUserIdList(List<String> invalidOpenUserIdList) {
        this.invalidOpenUserIdList = invalidOpenUserIdList;
    }
}
