package com.github.shuaidd.resquest.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 新增员工可重复字段组请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class StaffGroupInsert {
    /**
     * 需要新增的可重复字段组类型
     **/
    @JsonProperty("group_type")
    private Integer groupType;

    /**
     * 需要新增的组内字段内容列表
     **/
    @JsonProperty("item")
    private List<StaffFieldValue> item;
}
