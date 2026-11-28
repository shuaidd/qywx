package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 更新应用邮箱账号请求。
 */
public class UpdateEmailAliasRequest {

    /**
     * 修改后的应用邮箱账号；原账号会变更为可收信的别名
     */
    @JsonProperty("new_email")
    private String newEmail;

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
