package com.github.shuaidd.response.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.security.AdminOperLog;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理端操作日志列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminOperLogListResponse extends AbstractBaseResponse {
    /**
     * 管理端操作日志记录列表
     **/
    @JsonProperty("record_list")
    private List<AdminOperLog> recordList;
}