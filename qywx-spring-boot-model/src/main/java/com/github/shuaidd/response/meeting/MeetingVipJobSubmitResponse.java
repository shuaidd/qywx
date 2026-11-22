package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 高级功能账号批量任务提交响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingVipJobSubmitResponse extends AbstractBaseResponse {
    /**
     * 批量分配或撤销分配高级功能的任务id
     **/
    @JsonProperty("jobid")
    private String jobId;

    /**
     * 不在应用可见范围或无法识别的userid列表
     **/
    @JsonProperty("invalid_userid_list")
    private List<String> invalidUseridList;
}
