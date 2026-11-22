package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 应用可用家长范围响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolAgentAllowScopeResponse extends AbstractBaseResponse {

    /**
     * 可在微信「学校通知-学校应用」使用该应用的家长范围
     */
    @JsonProperty("allow_scope")
    private SchoolAgentAllowScope allowScope;

    public SchoolAgentAllowScope getAllowScope() {
        return allowScope;
    }

    public void setAllowScope(SchoolAgentAllowScope allowScope) {
        this.allowScope = allowScope;
    }
}
