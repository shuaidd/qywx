package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 按userid批量操作的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UserIdListRequest {

    /**
     * 家校通讯录账号列表，最多100个
     */
    @JsonProperty("useridlist")
    private List<String> userIdList;

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
