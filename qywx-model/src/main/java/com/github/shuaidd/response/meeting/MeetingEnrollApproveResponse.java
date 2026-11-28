package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议报名审批响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingEnrollApproveResponse extends AbstractBaseResponse {

    /**
     * 成功处理的报名记录数量
     **/
    @JsonProperty("handled_count")
    private Integer handledCount;
}