package com.github.shuaidd.resquest.government;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取用户负责及参与网格请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class UserGridInfoRequest {

    /**
     * 需要查询的成员userid
     */
    @JsonProperty("userid")
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
