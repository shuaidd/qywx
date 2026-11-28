package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 花名册字段组配置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class HrFieldGroup {
    /**
     * 字段组id
     **/
    @JsonProperty("group_id")
    private Integer groupId;

    /**
     * 字段组名称
     **/
    @JsonProperty("group_name")
    private String groupName;

    /**
     * 字段组包含的字段配置列表
     **/
    @JsonProperty("field_list")
    private List<HrFieldConfig> fieldList;
}
