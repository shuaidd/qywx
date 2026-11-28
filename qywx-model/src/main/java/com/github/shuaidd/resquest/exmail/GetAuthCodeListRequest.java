package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询公共邮箱客户端专用密码列表请求。
 */
public class GetAuthCodeListRequest {

    /**
     * 公共邮箱 ID
     */
    @JsonProperty("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
