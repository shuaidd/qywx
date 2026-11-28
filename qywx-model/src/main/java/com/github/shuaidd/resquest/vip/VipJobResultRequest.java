package com.github.shuaidd.resquest.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 高级功能账号任务结果查询请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class VipJobResultRequest {
    /**
     * 批量分配或撤销分配高级功能的任务id
     **/
    @JsonProperty("jobid")
    private String jobId;
}
