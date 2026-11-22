package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议投票实例信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingPollInfo {

    /**
     * 投票 ID
     **/
    @JsonProperty("poll_id")
    private String pollId;

    /**
     * 投票标题
     **/
    @JsonProperty("poll_topic")
    private String pollTopic;

    /**
     * 投票状态：1 投票中、2 已结束
     **/
    @JsonProperty("status")
    private Integer status;

    /**
     * 是否共享投票结果
     **/
    @JsonProperty("is_shared")
    private Integer isShared;

    /**
     * 是否匿名投票：0 实名、1 匿名
     **/
    @JsonProperty("is_anony")
    private Integer isAnony;
}
