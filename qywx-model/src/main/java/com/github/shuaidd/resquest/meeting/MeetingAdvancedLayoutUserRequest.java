package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取用户高级布局请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingAdvancedLayoutUserRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 被操作用户临时ID
     **/
    @JsonProperty("tmp_openid")
    private String tmpOpenid;
    /**
     * 被操作用户终端设备类型ID
     **/
    @JsonProperty("instance_id")
    private Integer instanceId;
}
