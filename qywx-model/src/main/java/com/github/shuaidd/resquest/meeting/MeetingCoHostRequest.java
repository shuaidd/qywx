package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置或撤销联席主持人请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingCoHostRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * true 设置联席主持人，false 撤销联席主持人
     **/
    @JsonProperty("action")
    private Boolean action;

    /**
     * 被操作的会议成员
     **/
    @JsonProperty("operated_user")
    private MeetingRealControlUser operatedUser;
}
