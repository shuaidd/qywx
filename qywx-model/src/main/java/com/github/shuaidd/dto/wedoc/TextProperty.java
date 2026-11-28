package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class TextProperty {

    /**
     * 是否加粗
     */
    @JsonProperty("bold")
    private Boolean bold;
    /**
     * 文字颜色，十六进制RRGGBB格式
     */
    @JsonProperty("color")
    private String color;
    /**
     * 文字的背景颜色，十六进制RRGGBB 格式
     */
    @JsonProperty("background_color")
    private String backgroundColor;
}
