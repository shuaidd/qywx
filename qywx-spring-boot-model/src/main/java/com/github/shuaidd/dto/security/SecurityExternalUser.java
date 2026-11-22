package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 安全管理企业外部人员信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class SecurityExternalUser {
    /**
     * 外部人员类型
     **/
    @JsonProperty("type")
    private Integer type;

    /**
     * 外部人员名称
     **/
    @JsonProperty("name")
    private String name;

    /**
     * 外部人员所属企业名称
     **/
    @JsonProperty("corp_name")
    private String corpName;
}
