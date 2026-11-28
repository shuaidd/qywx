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
public class UpdateTextProperty {

    /**
     * 文本属性
     */
    @JsonProperty("text_property")
    private TextProperty textProperty;
    /**
     * 更新文本属性的范围，ranges个数不超过10
     */
    @JsonProperty("ranges")
    private List<Range> ranges;
}
