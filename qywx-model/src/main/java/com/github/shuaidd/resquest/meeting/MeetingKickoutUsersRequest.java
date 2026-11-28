package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 移出会议成员请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingKickoutUsersRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 被移出后是否允许成员再次入会
     **/
    @JsonProperty("allow_rejoin")
    private Boolean allowRejoin;

    /**
     * 需要移出的会议成员列表
     **/
    @JsonProperty("operated_users")
    private List<MeetingRealControlUser> operatedUsers;
}
