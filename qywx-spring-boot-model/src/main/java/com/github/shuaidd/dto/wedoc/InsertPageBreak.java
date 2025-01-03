package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class InsertPageBreak {

    /**
     * 插入的位置
     */
    @JsonProperty("location")
    private Location location;
}
