package com.github.shuaidd.response.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.security.SecurityOperLog;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 成员操作记录列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberOperLogListResponse extends AbstractBaseResponse {
    /**
     * 成员操作记录列表
     **/
    @JsonProperty("record_list")
    private List<SecurityOperLog> recordList;
}