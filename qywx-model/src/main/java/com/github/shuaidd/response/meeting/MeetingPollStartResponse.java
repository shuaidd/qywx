package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发起会议投票响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPollStartResponse extends AbstractBaseResponse {

    /**
     * 新发起的投票 ID
     **/
    @JsonProperty("poll_id")
    private String pollId;
}
