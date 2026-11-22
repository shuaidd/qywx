package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 切换会议成员视频状态请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingUserVideoRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * true 开启视频（仅 MRA 设备），false 关闭视频
     **/
    @JsonProperty("video")
    private Boolean video;

    /**
     * 被操作的会议成员
     **/
    @JsonProperty("operated_user")
    private MeetingRealControlUser operatedUser;
}
