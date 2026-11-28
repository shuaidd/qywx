package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 员工可重复字段组操作结果
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class StaffGroupOperationResult {
    /**
     * 可重复字段组类型
     **/
    @JsonProperty("group_type")
    private Integer groupType;

    /**
     * 删除时的字段组下标；新增时为输入下标
     **/
    @JsonProperty("sub_idx")
    private Integer subIdx;

    /**
     * 新增字段组在结果中的下标
     **/
    @JsonProperty("idx")
    private Integer idx;

    /**
     * 操作结果：1成功、2失败、3字段组或下标未找到、4必填字段未填写、5不支持更新的字段类型
     **/
    @JsonProperty("result")
    private Integer result;
}
