package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms呼叫应答状态响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsResponseStatusResponse extends AbstractBaseResponse {
    /**
     * 应答状态：0无应答、1未呼叫、2入会中、3被拒绝、4呼叫中、5取消呼叫、6已离会
     **/
    @JsonProperty("status")
    private Integer status;
    /**
     * 最近一次应答时间
     **/
    @JsonProperty("response_time")
    private String responseTime;
}
