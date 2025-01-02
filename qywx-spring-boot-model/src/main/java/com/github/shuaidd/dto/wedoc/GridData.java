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
public class GridData {

    /**
     * 起始行编号 （从0开始计算）
     */
    @JsonProperty("start_row")
    private Integer startRow;
    /**
     * 起始列编号 （从0开始计算）
     */
    @JsonProperty("start_column")
    private Integer startColumn;

    private List<RowData> rows;
}
