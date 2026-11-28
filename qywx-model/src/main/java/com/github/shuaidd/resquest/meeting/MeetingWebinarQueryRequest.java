package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 按 ID 或会议号查询网络研讨会请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingWebinarQueryRequest {
    /**
     * 网络研讨会 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 网络研讨会会议号
     **/
    @JsonProperty("meeting_code")
    private String meetingCode;
}
