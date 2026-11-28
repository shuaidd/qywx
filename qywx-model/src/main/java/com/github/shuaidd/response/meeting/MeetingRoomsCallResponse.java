package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 呼叫Rooms会议室响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsCallResponse extends AbstractBaseResponse {
    /**
     * 呼叫ID
     **/
    @JsonProperty("invite_id")
    private String inviteId;
}
