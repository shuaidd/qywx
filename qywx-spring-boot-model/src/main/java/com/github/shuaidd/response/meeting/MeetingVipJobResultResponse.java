package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingVipJobResult;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 高级功能账号批量任务结果响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingVipJobResultResponse extends AbstractBaseResponse {
    /**
     * 高级功能账号批量任务执行结果详情
     **/
    @JsonProperty("job_result")
    private MeetingVipJobResult jobResult;
}
