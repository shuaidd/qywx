package com.github.shuaidd.response.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 更新模版卡片消息响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UpdateTemplateCardResponse extends AbstractBaseResponse {

    @JsonProperty("invaliduser")
    private List<String> invalidUser;

    public List<String> getInvalidUser() { return invalidUser; }
    public void setInvalidUser(List<String> invalidUser) { this.invalidUser = invalidUser; }
}