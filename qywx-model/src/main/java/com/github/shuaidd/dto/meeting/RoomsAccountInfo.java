package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms会议室账号信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsAccountInfo {
    /**
     * 账号类型：0普通、1专款、2试用
     **/
    @JsonProperty("account_type")
    private Integer accountType;
    /**
     * 有效期限
     **/
    @JsonProperty("valid_period")
    private String validPeriod;
}
