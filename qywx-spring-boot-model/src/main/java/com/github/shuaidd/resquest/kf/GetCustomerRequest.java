package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-25 11:42
 **/
public class GetCustomerRequest {

    @JsonProperty("external_userid_list")
    private List<String> externalUserIdList;

    public List<String> getExternalUserIdList() {
        return externalUserIdList;
    }

    public void setExternalUserIdList(List<String> externalUserIdList) {
        this.externalUserIdList = externalUserIdList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetCustomerRequest.class.getSimpleName() + "[", "]")
                .add("externalUserIdList=" + externalUserIdList)
                .toString();
    }
}
