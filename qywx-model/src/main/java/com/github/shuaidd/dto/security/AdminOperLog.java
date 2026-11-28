package com.github.shuaidd.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理端操作记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminOperLog extends SecurityOperLog {
    /**
     * 管理端操作行为类型
     **/
    @JsonProperty("detail_type")
    private Integer detailType;
}