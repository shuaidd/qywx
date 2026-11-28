package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 员工花名册字段操作结果
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class StaffFieldOperationResult {
    /**
     * 员工字段id
     **/
    @JsonProperty("fieldid")
    private Integer fieldId;

    /**
     * 可重复字段组中的字段下标
     **/
    @JsonProperty("sub_idx")
    private Integer subIdx;

    /**
     * 操作结果：1成功、2失败、3字段或下标未找到、4必填字段未填写、5不支持更新的字段类型
     **/
    @JsonProperty("result")
    private Integer result;
}
