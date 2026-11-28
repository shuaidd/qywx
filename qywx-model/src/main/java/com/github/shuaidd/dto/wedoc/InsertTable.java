package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ddshuai
 */
@NoArgsConstructor
@Data
public class InsertTable {

    /**
     * 表格行数
     */
    @JsonProperty("rows")
    private Integer rows;
    /**
     * 表格列数
     */
    @JsonProperty("cols")
    private Integer cols;
    /**
     * 插入的位置
     */
    @JsonProperty("location")
    private Location location;
}
