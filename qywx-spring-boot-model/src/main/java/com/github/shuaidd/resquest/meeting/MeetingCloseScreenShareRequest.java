package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 停止会议成员屏幕共享请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingCloseScreenShareRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 正在共享屏幕的会议成员
     **/
    @JsonProperty("operated_user")
    private MeetingRealControlUser operatedUser;
}
