package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 审批会议报名请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingEnrollApproveRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 审批动作：1 取消批准、2 拒绝、3 批准
     **/
    @JsonProperty("action")
    private Integer action;

    /**
     * 报名 ID 列表
     **/
    @JsonProperty("enroll_id_list")
    private List<String> enrollIdList;
}