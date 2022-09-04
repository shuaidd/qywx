package com.github.shuaidd.response.linkedcorp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 * date 2022-09-04 21:27
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedCorpUserResponse extends AbstractBaseResponse {

    @JsonProperty("user_info")
    private LinkedGroupUser userInfo;

    public LinkedGroupUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LinkedGroupUser userInfo) {
        this.userInfo = userInfo;
    }
}
