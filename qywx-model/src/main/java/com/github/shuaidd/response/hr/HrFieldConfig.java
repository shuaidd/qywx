package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 花名册字段配置
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class HrFieldConfig {
    /**
     * 员工字段id
     **/
    @JsonProperty("fieldid")
    private Integer fieldId;

    /**
     * 员工字段名称
     **/
    @JsonProperty("field_name")
    private String fieldName;

    /**
     * 字段类型：1文本、2选项、3时间、4图片、5单个文件、6多个文件
     **/
    @JsonProperty("field_type")
    private Integer fieldType;

    /**
     * 字段值类型
     **/
    @JsonProperty("value_type")
    private Integer valueType;

    /**
     * 字段是否必填
     **/
    @JsonProperty("is_must")
    private Boolean must;

    /**
     * 选项类型字段的枚举值列表
     **/
    @JsonProperty("option_list")
    private List<HrFieldOption> optionList;
}
