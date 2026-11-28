package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRealControlUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 管理会议等候室成员请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingWaitingRoomManageRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 操作类型：1 移入会议、2 移入等候室、3 移出等候室
     **/
    @JsonProperty("operate_type")
    private Integer operateType;

    /**
     * 移出等候室后是否允许再次加入会议
     **/
    @JsonProperty("allow_rejoin")
    private Boolean allowRejoin;

    /**
     * 被操作的等候室或会议成员列表
     **/
    @JsonProperty("operated_users")
    private List<MeetingRealControlUser> operatedUsers;
}
