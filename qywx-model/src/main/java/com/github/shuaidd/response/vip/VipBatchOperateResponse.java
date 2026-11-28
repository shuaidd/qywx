package com.github.shuaidd.response.vip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 高级功能账号批量操作响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class VipBatchOperateResponse extends AbstractBaseResponse {
    /**
     * 安全管理异步批量分配或撤销分配高级功能的任务id
     **/
    @JsonProperty("jobid")
    private String jobId;

    /**
     * 不在应用可见范围或无法识别的userid列表
     **/
    @JsonProperty("invalid_userid_list")
    private List<String> invalidUseridList;

    /**
     * 分配或撤销分配成功的userid列表
     **/
    @JsonProperty("succ_userid_list")
    private List<String> succUseridList;

    /**
     * 分配或撤销分配失败的userid列表
     **/
    @JsonProperty("fail_userid_list")
    private List<String> failUseridList;
}
