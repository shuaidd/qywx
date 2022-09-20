package com.github.shuaidd.response.linkedcorp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.linkedcorp.SimpleUser;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-04 21:37
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedCorpDeptUserResponse  extends AbstractBaseResponse {

    @JsonProperty("userlist")
    private List<SimpleUser> userList;

    public List<SimpleUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SimpleUser> userList) {
        this.userList = userList;
    }
}
