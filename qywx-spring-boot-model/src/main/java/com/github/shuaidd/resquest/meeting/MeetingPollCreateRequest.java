package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingPollQuestion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 创建会议投票请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingPollCreateRequest {

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
     * 投票主题，最长 50 个字符
     **/
    @JsonProperty("poll_topic")
    private String pollTopic;

    /**
     * 投票主题描述，最长 100 个字符
     **/
    @JsonProperty("poll_desc")
    private String pollDesc;

    /**
     * 是否匿名投票：0 实名、1 匿名
     **/
    @JsonProperty("is_anony")
    private Integer isAnony;

    /**
     * 投票问题列表，最多 10 个问题
     **/
    @JsonProperty("poll_questions")
    private List<MeetingPollQuestion> pollQuestions;
}
