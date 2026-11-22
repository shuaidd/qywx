package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingAttendee;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 会议成员列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingAttendeeListResponse extends AbstractBaseResponse {

    /**
     * 实时会议状态
     **/
    @JsonProperty("status")
    private String status;

    /**
     * 会议成员列表
     **/
    @JsonProperty("attendees")
    private List<MeetingAttendee> attendees;
}
