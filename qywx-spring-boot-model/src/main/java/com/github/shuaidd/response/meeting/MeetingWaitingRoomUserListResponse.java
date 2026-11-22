package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingWaitingRoomUser;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 等候室成员列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingWaitingRoomUserListResponse extends AbstractBaseResponse {

    /**
     * 等候室成员列表
     **/
    @JsonProperty("user_list")
    private List<MeetingWaitingRoomUser> userList;
}