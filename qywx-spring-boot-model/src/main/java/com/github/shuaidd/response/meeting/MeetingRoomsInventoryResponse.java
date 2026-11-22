package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms会议室资源响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsInventoryResponse extends AbstractBaseResponse {
    /**
     * 普通设备总数
     **/
    @JsonProperty("normal_count")
    private Integer normalCount;
    /**
     * 专款设备总数
     **/
    @JsonProperty("special_count")
    private Integer specialCount;
    /**
     * 普通设备使用数
     **/
    @JsonProperty("normal_used_count")
    private Integer normalUsedCount;
    /**
     * 专款设备使用数
     **/
    @JsonProperty("special_used_count")
    private Integer specialUsedCount;
    /**
     * 普通设备过期数
     **/
    @JsonProperty("normal_expired_count")
    private Integer normalExpiredCount;
    /**
     * 专款设备过期数
     **/
    @JsonProperty("special_expired_count")
    private Integer specialExpiredCount;
}
