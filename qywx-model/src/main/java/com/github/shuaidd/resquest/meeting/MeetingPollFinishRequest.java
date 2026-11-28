package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 结束会议投票请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingPollFinishRequest {

    /**
     * 操作者 openid
     **/
    @JsonProperty("operator_userid")
    private String operatorUserId;

    /**
     * 操作者入会使用的终端设备类型
     **/
    @JsonProperty("instance_id")
    private Integer instanceId;

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 投票主题 ID
     **/
    @JsonProperty("poll_theme_id")
    private String pollThemeId;

    /**
     * 需要结束的投票 ID
     **/
    @JsonProperty("poll_id")
    private String pollId;
}
