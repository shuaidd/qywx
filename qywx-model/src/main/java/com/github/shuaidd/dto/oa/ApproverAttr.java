package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 13:37
 **/
public class ApproverAttr {

    private String attr;

    @JsonProperty("userid")
    private String userId;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
