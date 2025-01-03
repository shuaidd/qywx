package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ddshuai
 */

@Data
@Accessors(chain = true)
public class TextFormat {

    /**
     * 字体名称，表格支持的字体类型: 字体列表
     */
    @JsonProperty("font")
    private String font;
    /**
     * 字体大小，最大72
     */
    @JsonProperty("font_size")
    private Integer fontSize;
    /**
     * 字体加粗
     */
    @JsonProperty("bold")
    private Boolean bold;
    /**
     * 斜体
     */
    @JsonProperty("italic")
    private Boolean italic;
    /**
     * 字体删除线
     */
    @JsonProperty("strikethrough")
    private Boolean strikethrough;
    /**
     * 下划线
     */
    @JsonProperty("underline")
    private Boolean underline;

    /**
     * 字体颜色
     */
    @JsonProperty("color")
    private Color color;
}
