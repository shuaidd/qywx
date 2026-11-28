package com.github.shuaidd.response.hr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 花名册字段选项
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class HrFieldOption {
    /**
     * 选项枚举值
     **/
    @JsonProperty("id")
    private Integer id;

    /**
     * 选项文本内容
     **/
    @JsonProperty("value")
    private String value;
}
