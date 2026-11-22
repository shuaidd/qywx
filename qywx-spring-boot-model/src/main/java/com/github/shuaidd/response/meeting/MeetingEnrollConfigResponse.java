package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingQuestion;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 会议报名配置响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingEnrollConfigResponse extends AbstractBaseResponse {

    /**
     * 报名审批类型：1 自动审批、2 手动审批
     **/
    @JsonProperty("approve_type")
    private Integer approveType;

    /**
     * 是否收集报名问题：1 不收集、2 收集
     **/
    @JsonProperty("is_collect_question")
    private Integer isCollectQuestion;

    /**
     * 本企业成员是否无需报名
     **/
    @JsonProperty("no_registration_needed_for_staff")
    private Boolean noRegistrationNeededForStaff;

    /**
     * 报名问题列表
     **/
    @JsonProperty("question_list")
    private List<MeetingQuestion> questionList;

    /**
     * 设置接口返回的报名问题数量
     **/
    @JsonProperty("question_count")
    private Integer questionCount;
}