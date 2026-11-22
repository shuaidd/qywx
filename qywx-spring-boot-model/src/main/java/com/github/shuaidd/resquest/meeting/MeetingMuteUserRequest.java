package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 会议成员静音操作请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingMuteUserRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * true 静音成员，false 解除静音
     **/
    @JsonProperty("option")
    private Boolean option;

    /**
     * 被操作的会议成员
     **/
    @JsonProperty("operated_user")
    private MeetingRealControlUser operatedUser;
}
