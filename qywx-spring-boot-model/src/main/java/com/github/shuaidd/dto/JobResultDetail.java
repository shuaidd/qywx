package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-06 20:53
 **/
public class JobResultDetail {

    /**
     * type为sync_user、replace_user
     */
    @JsonProperty("userid")
    private String userId;

    /** type为replace_party */
    /**
     * 操作类型（按位或）：1 新建部门 ，2 更改部门名称， 4 移动部门， 8 修改部门排序
     */
    private Integer action;

    @JsonProperty("partyid")
    private Integer partyId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JobResultDetail.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("action=" + action)
                .add("partyId=" + partyId)
                .toString();
    }
}
