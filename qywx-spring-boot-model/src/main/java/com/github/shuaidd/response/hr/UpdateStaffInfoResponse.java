package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 更新员工花名册信息响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateStaffInfoResponse extends AbstractBaseResponse {
    /**
     * 字段更新结果列表
     **/
    @JsonProperty("update_results")
    private List<StaffFieldOperationResult> updateResults;

    /**
     * 可重复字段组删除结果列表
     **/
    @JsonProperty("remove_results")
    private List<StaffGroupOperationResult> removeResults;

    /**
     * 可重复字段组新增结果列表；官方返回示例字段为单数insert_result
     **/
    @JsonProperty("insert_result")
    private List<StaffGroupOperationResult> insertResult;
}
