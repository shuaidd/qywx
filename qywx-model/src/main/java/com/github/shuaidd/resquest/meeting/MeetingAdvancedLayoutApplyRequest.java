package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingLayoutUser;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 应用会议高级布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingAdvancedLayoutApplyRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 应用布局ID，空字符串恢复默认布局
     **/
    @JsonProperty("layout_id")
    private String layoutId;
    /**
     * 设置个性布局的用户列表
     **/
    @JsonProperty("user_list")
    private List<MeetingLayoutUser> userList;
}
