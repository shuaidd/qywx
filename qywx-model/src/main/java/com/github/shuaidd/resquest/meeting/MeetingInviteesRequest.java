package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 更新会议受邀成员请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingInviteesRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 受邀成员列表
     **/
    @JsonProperty("invitees")
    private List<UserId> invitees;
}
