package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingQuestion;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 设置会议报名配置请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingEnrollConfigRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

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
}