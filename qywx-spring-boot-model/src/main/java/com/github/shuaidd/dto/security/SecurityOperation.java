package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 安全管理操作类型信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class SecurityOperation {
    /**
     * 操作类型
     **/
    @JsonProperty("type")
    private Integer type;

    /**
     * 操作来源
     **/
    @JsonProperty("source")
    private Integer source;
}
