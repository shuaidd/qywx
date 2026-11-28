package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class ReplaceText {

    /**
     * 要替换的文本
     */
    @JsonProperty("text")
    private String text;
    /**
     * 要替换的文档范围，可同时替换多个位置的文本, rangs个数不超过10。
     */
    @JsonProperty("ranges")
    private List<Range> ranges;
}
