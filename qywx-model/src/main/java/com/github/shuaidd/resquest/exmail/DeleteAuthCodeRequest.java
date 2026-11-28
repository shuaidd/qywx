package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 删除公共邮箱客户端专用密码请求。
 */
public class DeleteAuthCodeRequest {

    /**
     * 公共邮箱 ID
     */
    @JsonProperty("id")
    private Integer id;

    /**
     * 客户端专用密码 ID
     */
    @JsonProperty("auth_code_id")
    private Integer authCodeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthCodeId() {
        return authCodeId;
    }

    public void setAuthCodeId(Integer authCodeId) {
        this.authCodeId = authCodeId;
    }
}
