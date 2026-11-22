package com.github.shuaidd.resquest.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 删除员工可重复字段组请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class StaffGroupDelete {
    /**
     * 需要删除的可重复字段组类型
     **/
    @JsonProperty("group_type")
    private Integer groupType;

    /**
     * 需要删除的第几组字段下标
     **/
    @JsonProperty("sub_idx")
    private Integer subIdx;
}
