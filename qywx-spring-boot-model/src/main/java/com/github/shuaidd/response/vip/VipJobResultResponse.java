package com.github.shuaidd.response.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.vip.VipJobResult;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 高级功能账号任务结果响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class VipJobResultResponse extends AbstractBaseResponse {
    /**
     * 高级功能账号批量任务执行结果详情
     **/
    @JsonProperty("job_result")
    private VipJobResult jobResult;
}
