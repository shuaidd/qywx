package com.github.shuaidd.dto.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-04 21:11
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserIdList {

    @JsonProperty("open_userid")
    private String openUserId;

    @JsonProperty("userid")
    private String userId;

    public String getOpenUserId() {
        return openUserId;
    }

    public void setOpenUserId(String openUserId) {
        this.openUserId = openUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
