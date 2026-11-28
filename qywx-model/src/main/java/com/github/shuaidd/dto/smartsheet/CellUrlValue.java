package com.github.shuaidd.dto.smartsheet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CellUrlValue {

    /**
     * 链接跳转url
     */
    @JsonProperty("link")
    private String link;
    /**
     * 链接显示文本
     */
    @JsonProperty("text")
    private String text;
    /**
     * 填url
     */
    @JsonProperty("type")
    private String type;
}
