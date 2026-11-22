package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingPollQuestionData;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 会议投票详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingPollDetailResponse extends AbstractBaseResponse {

    /**
     * 投票主题 ID
     **/
    @JsonProperty("poll_theme_id")
    private String pollThemeId;

    /**
     * 投票标题
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
     * 参与投票的人数
     **/
    @JsonProperty("vote_total_num")
    private Integer voteTotalNum;

    /**
     * 投票问题和结果列表
     **/
    @JsonProperty("poll_question_data")
    private List<MeetingPollQuestionData> pollQuestionData;
}
