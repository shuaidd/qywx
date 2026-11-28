package com.github.shuaidd.response.linkedcorp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-04 21:41
 **/
public class LinkedCorpDeptUserListResponse extends AbstractBaseResponse {

    @JsonProperty("userlist")
    private List<LinkedGroupUser> userList;

    public List<LinkedGroupUser> getUserList() {
        return userList;
    }

    public void setUserList(List<LinkedGroupUser> userList) {
        this.userList = userList;
    }
}
