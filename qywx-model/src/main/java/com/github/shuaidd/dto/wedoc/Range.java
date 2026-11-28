package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class Range {

    /**
     * 起始位置，从0开始
     */
    @JsonProperty("start_index")
    private Integer startIndex;
    /**
     * 长度
     */
    @JsonProperty("length")
    private Integer length;
}
