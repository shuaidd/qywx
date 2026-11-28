package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 修改会议成员昵称请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingNicknameRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 需要修改昵称的成员列表，nickname 字段表示新昵称
     **/
    @JsonProperty("operated_users")
    private List<MeetingRealControlUser> operatedUsers;
}
