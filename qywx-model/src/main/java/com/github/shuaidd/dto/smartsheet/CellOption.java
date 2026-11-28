package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CellOption {

    /**
     * 选项ID，当选项存在时，通过ID识别选项，当需要新增选项，则不填写此字段
     */
    @JsonProperty("id")
    private String id;
    /**
     * 选项颜色。新增选项时填写
     */
    @JsonProperty("text")
    private String text;
    /**
     * 要填写的选项内容。新增选项时填写，已经存在时优先匹配已经存在的选项，否则会新增选项
     */
    @JsonProperty("style")
    private Integer style;
}
