package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-05 18:07
 **/
public class BatchDeleteUserRequest {

    @JsonProperty("useridlist")
    private List<String> userIdList;

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BatchDeleteUserRequest.class.getSimpleName() + "[", "]")
                .add("userIdList=" + userIdList)
                .toString();
    }
}
