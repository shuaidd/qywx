package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 删除会议投票请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingPollDeleteRequest {

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
     * 投票主题 ID；传入时删除投票主题，与投票 ID 二选一，同时传入时以投票 ID 优先
     **/
    @JsonProperty("poll_theme_id")
    private String pollThemeId;

    /**
     * 投票 ID；传入时删除投票实例，与投票主题 ID 二选一，同时传入时以本字段优先
     **/
    @JsonProperty("poll_id")
    private String pollId;
}
