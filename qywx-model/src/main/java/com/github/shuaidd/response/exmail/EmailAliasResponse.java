package com.github.shuaidd.response.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 应用邮箱账号查询响应。
 */
public class EmailAliasResponse extends AbstractBaseResponse {

    /**
     * 当前发信账号主邮箱地址
     */
    @JsonProperty("email")
    private String email;

    /**
     * 可收信的别名邮箱地址列表
     */
    @JsonProperty("alias_list")
    private List<String> aliasList;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAliasList() {
        return aliasList;
    }

    public void setAliasList(List<String> aliasList) {
        this.aliasList = aliasList;
    }
}
