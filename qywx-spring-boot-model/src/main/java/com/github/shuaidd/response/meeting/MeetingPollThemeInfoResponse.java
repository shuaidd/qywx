package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingPollQuestionData;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 会议投票主题详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPollThemeInfoResponse extends AbstractBaseResponse {

    /**
     * 投票主题
     **/
    @JsonProperty("poll_topic")
    private String pollTopic;

    /**
     * 投票主题描述
     **/
    @JsonProperty("poll_desc")
    private String pollDesc;

    /**
     * 是否匿名投票：0 实名、1 匿名
     **/
    @JsonProperty("is_anony")
    private Integer isAnony;

    /**
     * 投票问题和选项列表
     **/
    @JsonProperty("poll_question_data")
    private List<MeetingPollQuestionData> pollQuestionData;
}
